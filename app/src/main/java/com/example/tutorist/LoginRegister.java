package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginRegister extends AppCompatActivity {

    private Button bt;
    private Button bt2;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);

        //if user wants to create an account -> GO MAKE IT
        bt = (Button) findViewById(R.id.create_account);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChooseUserType(v);
            }
        });

        //if user is logging in, go to dashboard
        bt2 = (Button) findViewById(R.id.login);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void goToChooseUserType (View v) {
        startActivity(new Intent(LoginRegister.this, ChooseUser.class));
    }
}
