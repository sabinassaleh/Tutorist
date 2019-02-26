package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegisterStudent extends AppCompatActivity {

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
    }

    public void goToRegisterSubjectsS (View v) {
        startActivity(new Intent(RegisterStudent.this, RegisterSubjectsStudent.class));
    }
}
