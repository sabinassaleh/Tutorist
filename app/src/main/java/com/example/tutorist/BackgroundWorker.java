package com.example.tutorist;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class BackgroundWorker extends AsyncTask<String, Void, Void> {

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

    private Connection connection;

    Context context;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected Void doInBackground(String... params) {
        String firstName = params[1];
        String lastName = params[2];
        String email = params[3];
        String password = params[4];

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + PUBLIC_DNS + ":" + PORT + "/" +
                    DATABASE, REMOTE_DATABASE_USERNAME, DATABASE_USER_PASSWORD);
            System.out.println("TEST" + connection);
        }catch (SQLException e){
            System.out.println("Connection Failed:\n" + e.getMessage());
        }
        Statement stat_table;
        if (connection != null) {
            System.out.println("Success!!!");
        }else {
            System.out.println("FAIL");
        }
        return null;
       /* String type = params[0];

        if(type.equals("signup")) {
           *//* try {
                String firstName = params[1];
                String lastName = params[2];
                String email = params[3];
                String password = params[4];
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }*//*
        } else if (type.equals("login")) {
            *//*try {
                String email = params[1];
                String password = params[2];
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }*//*
        }


        if (type.equals("signup")) {

        }
        return null;*/
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("-------Connection Testing");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is Driver?");
            e.printStackTrace();
        }
        System.out.println("Driver Registered");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private static void runTestQUery(Connection conn) {

    }

    public CognitoCachingCredentialsProvider getcredentials(Context ctx) {
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                ctx,
                "us-east-2:0fa18e2c-cdc9-41de-9425-05ed1d0ccc46", // Identity pool ID
                Regions.US_EAST_2 // Region
        );
        return credentialsProvider;
    }
}
