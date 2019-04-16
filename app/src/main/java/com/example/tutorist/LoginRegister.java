package com.example.tutorist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginRegister extends AppCompatActivity {

    private Button bt;
    private Button bt2;
    SharedPreferences preferences;
    TextView email, password;
    private Spinner spinner;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);

        //if user wants to create an account -> GO MAKE IT
        bt = (Button) findViewById(R.id.create_account);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChooseUserType(v);
            }
        });

        //if user is logging in, go to dashboard
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        preferences = this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);

        bt2 = (Button) findViewById(R.id.login);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDashboard(v);
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);

    }

    public void goToChooseUserType (View v) {
        startActivity(new Intent(LoginRegister.this, ChooseUser.class));
    }

    public void goToDashboard(View v) {
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();

      String type = "login";
      String user = spinner.getSelectedItem().toString();

      //if user type is tutor/student, go to tutor's/student's background worker
      if (!str_email.isEmpty() && !str_password.isEmpty()) {
          if (user.equals("Tutor")) {
              BackgroundWorker backgroundWorker = new BackgroundWorker(LoginRegister.this);
              backgroundWorker.execute(type, str_email, str_password);

              SharedPreferences.Editor editor = preferences.edit();
              editor.putString("Email", str_email);
              editor.commit();
          }
          if (user.equals("Student")) {
              BackgroundWorker_Student backgroundWorker_student = new BackgroundWorker_Student(LoginRegister.this);
              backgroundWorker_student.execute(type, str_email, str_password);
          }
      } else {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_LONG).show();
      }
    }
}
