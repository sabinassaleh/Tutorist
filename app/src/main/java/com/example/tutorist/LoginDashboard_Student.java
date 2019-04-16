package com.example.tutorist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class LoginDashboard_Student extends AppCompatActivity {

    private static final String TUT_URL="http://ec2-52-14-130-163.us-east-2.compute.amazonaws.com/get_tutors.php";

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_dashboard_student);

        listView = (ListView) findViewById(R.id.tutors_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        new Connection().execute();
        //compare student's subject with those of tutors'
        //if found match: Display the tutor's information in the dashboard
        //if not found: Display message-> "Sorry, check back later for a tutor"

        //maybe: make new background worker for this section
        //     : create a php file that gets data from database
        //     : get student's subjects and find a tutor

    }
    class Connection extends AsyncTask<String, String, String> {

        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        @Override
        protected String doInBackground(String... params) {

            String result = "";

            try {
                URL url = new URL(TUT_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line = "";
                while((line = reader.readLine()) != null) {
                    result+=line;
                }
                reader.close();
                inputStream.close();
                conn.disconnect();

              System.out.println("Connected!!!!!!!!!!!!!!!!!!!");
                return result;
            }catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        //json parsing data from database to student's dashboard
        @Override
        protected void onPostExecute(String result) {
            preferences = getApplicationContext().getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
            String s1 = preferences.getString("Subject1", null);
            String s2 = preferences.getString("Subject2", null);
            String s3 = preferences.getString("Subject3", null);

            try {
               JSONObject js = new JSONObject(result);
               int success = js.getInt("success");
               if (success ==1) {
                  /* JSONArray student_info = js.getJSONArray("Student");
                   JSONObject ind_student_info = student_info.getJSONObject(0);

                   String Subject1 = ind_student_info.getString("FName");
                   String Subject2 = ind_student_info.getString("Subject2");
                   String Subject3 = ind_student_info.getString("Subject3");

                   String line = Subject1 +"";
                   adapter.add(line);*/
                    JSONArray tutors = js.getJSONArray("Tutor");
                    for (int i = 0; i < tutors.length(); i++) {
                        JSONObject tutor = tutors.getJSONObject(i);
                        String FName    = tutor.getString("FName");
                        String LName    = tutor.getString("LName");
                        String Email    = tutor.getString("Email");
                        String Subject1 = tutor.getString("Subject1");
                        String Subject2 = tutor.getString("Subject2");
                        String Subject3 = tutor.getString("Subject3");
                        //if student's subjects = tutor's subjects, display info

                        String line = FName + " " + LName + " " + Email + " " + Subject1 + " " + Subject2 + " " + Subject3;
                        adapter.add(line);

                    }
               }
               else {
                   Toast.makeText(getApplicationContext(), "No tutors", Toast.LENGTH_LONG).show();
               }

           }catch(JSONException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
           }
        }
    }
  /*  private void getData() {

        try {
            URL url = new URL(TUT_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            is = new BufferedInputStream(conn.getInputStream());

        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            is.close();
            result = sb.toString();
        }catch(Exception e) {
            e.printStackTrace();
        }

        //JSON parsing
        try {
            JSONArray js = new JSONArray(result);
            JSONObject jo = null;
            fName = new String[js.length()];
            lName = new String[js.length()];
            email = new String[js.length()];

            for (int i = 0; i < js.length(); i++) {
                jo = js.getJSONObject(i);
                fName[i] = jo.getString("FName");
                lName[i] = jo.getString("LName");
                email[i] = jo.getString("Email");

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
