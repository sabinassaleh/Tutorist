package com.example.tutorist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginDashboard extends AppCompatActivity {

    private static final String STUD_URL="http://ec2-52-14-130-163.us-east-2.compute.amazonaws.com/update_subjects.php";

    private Button bt;
    private EditText newSub;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_dashboard);

        newSub = (EditText) findViewById(R.id.new_sub);
        preferences = this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);

        bt = (Button) findViewById(R.id.update);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdate(v);
            }
        });
    }

    public void goToUpdate(View v) {
        String str_newsub = newSub.getText().toString();

        String type = "update";

        if (str_newsub.isEmpty()) {
            Toast.makeText(this, "Please enter a new subject.", Toast.LENGTH_LONG).show();
        }
        BackgroundWorker backgroundWorker = new BackgroundWorker(LoginDashboard.this);
        backgroundWorker.execute(type, str_newsub);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Subject1", str_newsub);
        editor.commit();

    }
}
