package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterStudent extends AppCompatActivity {

    private EditText firstName, lastName, email, password;
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
    }

    public void goToRegisterSubjectsS (View v) {

        String str_firstName = firstName.getText().toString();
        String str_lastName = lastName.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String type = "signup";

        BackgroundWorker_Student ur=new BackgroundWorker_Student(RegisterStudent.this);
        ur.execute(type, str_firstName,str_lastName,str_email,str_password);

        startActivity(new Intent(RegisterStudent.this, RegisterSubjectsStudent.class));
    }
}
