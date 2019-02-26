package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user);

        //when user clicks/chooses "STUDENT"
        bt = (Button) findViewById(R.id.button5);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterInfoS(v);
                //setContentView(R.layout.student);
            }
        });
    }

    public void goToRegisterInfoS(View v) {
        startActivity(new Intent(Activity2.this, RegisterStudent.class));
    }

 }
