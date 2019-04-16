package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterStudent extends AppCompatActivity {

    private EditText firstName, lastName, email, password, sub1, sub2, sub3;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        bt = (Button) findViewById(R.id.nextButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterSubjectsS(v);
            }
        });
        firstName = (EditText) findViewById(R.id.student_firstName);
        lastName = (EditText) findViewById(R.id.student_lastName);
        email = (EditText) findViewById(R.id.student_email);
        password = (EditText) findViewById(R.id.student_pass);
        sub1 = (EditText) findViewById(R.id.student_sub1);
      /*  sub2 = (EditText) findViewById(R.id.student_sub2);
        sub3 = (EditText) findViewById(R.id.student_sub3);*/


    }

    public void goToRegisterSubjectsS (View v) {

        String str_firstName = firstName.getText().toString();
        String str_lastName = lastName.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String str_s1 = sub1.getText().toString();

        String type = "signup";

        //Checks if user provided all required information
        if (str_firstName.isEmpty() || str_lastName.isEmpty() || str_email.isEmpty() ||
                str_password.isEmpty() || str_s1.isEmpty()) {
            Toast.makeText(this, "Please provide all information", Toast.LENGTH_SHORT).show();
        }

        //CHECKS FOR VALID EMAIL ADDRESS
        else if (Patterns.EMAIL_ADDRESS.matcher(str_email).matches() == true) {
            BackgroundWorker_Student ur = new BackgroundWorker_Student(RegisterStudent.this);
            ur.execute(type, str_firstName, str_lastName, str_email, str_password, str_s1);

            startActivity(new Intent(RegisterStudent.this, RegisterDoneStudent.class));
        } else {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
        }
    }
}
