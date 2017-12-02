package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class User_Area_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__area_);

        final  EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        etUsername.setText(username);
        String message =  "welcome to your user area";
        welcomeMsg.setText(message);

    }
}
