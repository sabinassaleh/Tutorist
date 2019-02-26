package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user);

        //when user clicks/chooses "TUTOR"
        bt = (Button) findViewById(R.id.button4);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterInfo(v);
            }
        });
    }
    //changes frame for user to Register subjects
    public void goToRegisterInfo(View v) {
        startActivity(new Intent(Activity1.this, Register.class));

    }


}