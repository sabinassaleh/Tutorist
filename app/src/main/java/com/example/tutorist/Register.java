package com.example.tutorist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity {
    private EditText firstName, lastName, email, password, sub1, sub2, sub3;
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
        sub1 = (EditText) findViewById(R.id.tutor_sub1);
/*        sub2 = (EditText) findViewById(R.id.tutor_sub2);
        sub3 = (EditText) findViewById(R.id.tutor_sub3);*/
    }

    public void goToRegisterSubjects(View v) {
        String str_firstName = firstName.getText().toString();
        String str_lastName = lastName.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String str_s1 = sub1.getText().toString();
/*        String str_s2 = sub2.getText().toString();
        String str_s3 = sub3.getText().toString();*/

        String type = "signup";

        //Make sure user enters all details
        if (str_firstName.isEmpty() || str_lastName.isEmpty() || str_email.isEmpty() ||
        str_password.isEmpty() || str_s1.isEmpty()) {
            Toast.makeText(this, "Please provide all information", Toast.LENGTH_SHORT).show();
        }

        //CHECKS for valid email address
        else if (Patterns.EMAIL_ADDRESS.matcher(str_email).matches() == true) {
            BackgroundWorker ur=new BackgroundWorker(Register.this);
            ur.execute(type, str_firstName,str_lastName,str_email,str_password, str_s1);

            SharedPreferences.Editor editor = ur.preferences.edit();
            editor.putString("FName", str_firstName);
            editor.putString("LName", str_lastName);
            editor.putString("Email", str_email);
            editor.putString("Subject1", str_s1);
/*            editor.putString("Subject2", str_s2);
            editor.putString("Subject3", str_s3);*/
            editor.commit();

            startActivity(new Intent(Register.this, RegisterDoneTutor.class));

        }
        else {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
        }

    }
}
