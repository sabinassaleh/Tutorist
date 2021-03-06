package com.example.tutorist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button bt;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   //     mTextMessage = (TextView) findViewById(R.id.message);
   //     BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
   //     navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bt = (Button) findViewById(R.id.buttonPanel);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.choose_user);
            }
        });
    }

    public void start (View v) {
        startActivity(new Intent(MainActivity.this, Activity1.class));
    }
    public void start2 (View v) {
        startActivity(new Intent(MainActivity.this, Activity2.class));
    }




}
