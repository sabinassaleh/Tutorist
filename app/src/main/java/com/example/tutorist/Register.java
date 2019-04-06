package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity {
    private EditText firstName, lastName, email, password;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor);

        //when user clicks "NEXT"
        bt = (Button) findViewById(R.id.nextbutton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToRegisterSubjects(v);
            }
        });

        firstName = (EditText) findViewById(R.id.tutor_firstName);
        lastName = (EditText) findViewById(R.id.tutor_lastName);
        email = (EditText) findViewById(R.id.t_email);
        password = (EditText) findViewById(R.id.pass_t);
    }
 //   public void OnReg(View view) {

   // }

    public void goToRegisterSubjects(View v) {
        String str_firstName = firstName.getText().toString();
        String str_lastName = lastName.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String type = "signup";

        //Make sure user enters all details
        if (str_firstName.isEmpty() && str_lastName.isEmpty() && str_email.isEmpty() &&
        str_password.isEmpty()) {
            Toast.makeText(this, "Please provide all information", Toast.LENGTH_SHORT).show();
        }
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_firstName,str_lastName,str_email,str_password);



        startActivity(new Intent(Register.this, RegisterSubjectsTutor.class));
    }
}
