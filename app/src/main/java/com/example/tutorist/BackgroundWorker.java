package com.example.tutorist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

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
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class BackgroundWorker extends AsyncTask<String, Void, String> {

    private static final String PUBLIC_DNS = "tutoristdb.c6wmb6ek3rie.us-east-2.rds.amazonaws.com";
    private static final String PORT = "3306";
    private static final String DATABASE = "tutoristdb";
    private static final String REMOTE_DATABASE_USERNAME = "sssaleh";
    private static final String DATABASE_USER_PASSWORD = "Myinstance1";
    private static final String TABLE_NAME = "Tutor";

    private static final String TABLE_STUDENT = "Student";

    private static final String FIRST_NAME = "FName";
    private static final String LAST_NAME = "LName";
    private static final String EMAIL = "Email";
    private static final String PASS = "Password";

    private static final String TABLE_TUTOR = "Tutor";

    private static final String REGISTER_URL="http://ec2-52-14-130-163.us-east-2.compute.amazonaws.com/register.php";

    Context context;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        if (type.equals("signup")) {
            String firstName = params[1];
            String lastName = params[2];
            String email = params[3];
            String password = params[4];
            BufferedReader bufferedReader = null;
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
                        +"&"+URLEncoder.encode("Password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = conn.getInputStream();
                inputStream.close();


                return "Successfully Registered" + firstName + lastName;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("login")) {
            String email = params[1];
            String pass = params[2];
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
