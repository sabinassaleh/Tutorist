package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
 //   EditText firstName, lastName, email, phone;
 //   String str_firstName, str_lastName, str_email, str_phone;
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
            //REGISTER TO DATABASE !!!

            }
        });


   //     firstName = (EditText) findViewById(R.id.editText);
   //     lastName = (EditText) findViewById(R.id.editText2);
   //     email = (EditText) findViewById(R.id.editText3);
   //     phone = (EditText) findViewById(R.id.editText4);

    }
 //   public void OnReg(View view) {

   // }

    public void goToRegisterSubjects(View v) {
        startActivity(new Intent(Register.this, RegisterSubjectsTutor.class));

    }

}
