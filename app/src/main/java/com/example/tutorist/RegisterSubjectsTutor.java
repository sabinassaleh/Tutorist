package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterSubjectsTutor extends AppCompatActivity {

    private EditText sub1, sub2, sub3;
    private Button bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_subjects);

        bt = (Button) findViewById(R.id.Next);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTutorSchedule(v);
            }
        });

        sub1 = (EditText) findViewById(R.id.tutor_dept1);
        sub2 = (EditText) findViewById(R.id.tutor_dept2);
        sub3 = (EditText) findViewById(R.id.tutor_dept3);

    }

    public void goTutorSchedule(View v) {

        String s1 = sub1.getText().toString();
        String s2 = sub2.getText().toString();
        String s3 = sub3.getText().toString();

        String type = "registerSubs";

        BackgroundWorker bw = new BackgroundWorker(this);
        bw.execute(type, s1, s2, s3);

        startActivity(new Intent(RegisterSubjectsTutor.this, RegisterSchedule.class));
    }
}
