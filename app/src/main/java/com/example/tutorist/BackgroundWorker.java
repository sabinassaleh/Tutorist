package com.example.tutorist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class BackgroundWorker extends AsyncTask<String, Void, String>  {
    AlertDialog alertDialog;
    private static final String REGISTER_URL="http://ec2-52-14-130-163.us-east-2.compute.amazonaws.com/register.php";
    private static final String LOGIN_URL   ="http://ec2-52-14-130-163.us-east-2.compute.amazonaws.com/login.php";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    String dataResponse = "";


    Context context;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        preferences = context.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag","0");
        editor.commit();

        String type = params[0];

        if (type.equals("signup")) {
            String firstName = params[1];
            String lastName = params[2];
            String email = params[3];
            String password = params[4];
            String s1 = params[5];
            String s2 = params[6];
            String s3 = params[7];

            try {
                URL url = new URL(REGISTER_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                OutputStream outputStream = conn.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("FName", "UTF-8")+"="+URLEncoder.encode(firstName, "UTF-8")
                        +"&"+URLEncoder.encode("LName", "UTF-8")+"="+URLEncoder.encode(lastName, "UTF-8")
                        +"&"+URLEncoder.encode("Email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")
                        +"&"+URLEncoder.encode("Password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")
                        +"&"+URLEncoder.encode("Subject1","UTF-8")+"="+URLEncoder.encode(s1, "UTF-8")
                        +"&"+URLEncoder.encode("Subject2","UTF-8")+"="+URLEncoder.encode(s2, "UTF-8")
                        +"&"+URLEncoder.encode("Subject3","UTF-8")+"="+URLEncoder.encode(s3, "UTF-8")
                        ;
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = conn.getInputStream();
                inputStream.close();

                editor.putString("flag","signup");
                editor.commit();
                return "Successfully Registered" + firstName + " " + lastName;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("login")) {
            String email = params[1];
            String pass = params[2];
            try {
                URL url = new URL(LOGIN_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                //send email and pass to database
                OutputStream outputStream = conn.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("Email", "UTF-8") + "="
                        + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("Password", "UTF-8") + "="
                        + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get response from database
                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String inputLine = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                conn.disconnect();

                System.out.println(dataResponse);

                editor.putString("flag", "login");
                editor.commit();
                return dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.show();

    /*    String flag = preferences.getString("flag","0");
        if (flag.equals("login")) {
            String test = "false";
            String fName = "";
            String lName = "";
            String email = "";
            String[] serverResponse = s.split("[,]");
            test = serverResponse[0];
            email = serverResponse[1];
          *//*  lName = serverResponse[2];
            email = serverResponse[3];*//*

            if (test.equals("true")) {
               *//* editor.putString("FName", fName);
                editor.commit();
                editor.putString("LName", lName);
                editor.commit();*//*
                editor.putString("Email", email);
                editor.commit();
                Intent intent = new Intent(context, LoginDashboard.class);
                context.startActivity(intent);
            }else {
                Toast.makeText(context, "That email and/or password doesn't match our records. Please " +
                        "check your credentials or register as a new user.", Toast.LENGTH_LONG).show();

            }
        }*/

        String flag = preferences.getString("flag","0");
        if (flag.equals("login")) {
            if (dataResponse != null && dataResponse.equals("Login Successful. Welcome back Tutor!")) {
                Intent intent = new Intent(context, LoginDashboard.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "That email and/or password doesn't match our records. Please " +
                        "check your credentials or register as a new user.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}

