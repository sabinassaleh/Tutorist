package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseUser extends AppCompatActivity {

    private Button tutor;
    private Button student;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user);

        tutor = (Button) findViewById(R.id.button4);
        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterTutor(v);
            }
        });

        student = (Button) findViewById(R.id.button5);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterStudent(v);
            }
        });
    }

    public void goToRegisterTutor(View v) {
        startActivity(new Intent(ChooseUser.this, Register.class));
    }
    public void goToRegisterStudent(View v) {
        startActivity(new Intent(ChooseUser.this, RegisterStudent.class));
    }
}
