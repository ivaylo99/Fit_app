package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    private String email , name , username , password , repassword;
    private EditText etEmail , etName , etUsername , etPassword , etRePassword ;
    private TextView bLogin;
    Button bRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

         etEmail = (EditText) findViewById(R.id.etEmail);
         etName = (EditText) findViewById(R.id.etName);
         etUsername = (EditText) findViewById(R.id.etUsername);
         etPassword = (EditText) findViewById(R.id.etPassword);
         etRePassword = (EditText) findViewById(R.id.etRePassword);
         bRegister = (Button) findViewById(R.id.bRegister);
         bLogin = (TextView) findViewById(R.id.bLogin);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intent = new Intent(Register_Activity.this, Login_Activity.class);
                Register_Activity.this.startActivity(login_intent);
            }
        });
    }

    public void register() {
        initialize();
        if(!validate()) {
            Toast.makeText(this , "sign up has failed" ,Toast.LENGTH_SHORT).show();
        }
        else {
            onSignupSucces();
        }
    }

    public void onSignupSucces() {
                Intent login_intent = new Intent(Register_Activity.this, User_Area_Activity.class);
                Register_Activity.this.startActivity(login_intent);
    }

    public boolean validate() {
        boolean valid = true;

        if(name.isEmpty() || name.length() < 3 || name.length() > 20) {
            etName.setError("Enter name between 3 and 20 characters");
            valid = false;
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter valid email adress");
            valid = false;
        }

        if(username.isEmpty() || username.length() < 4 || username.length() > 12) {
            etUsername.setError("Username must be between 4 and 12 characters");
            valid = false;
        }

        if(password.isEmpty() || password.length() < 6 || password.length() > 15) {
            etPassword.setError("Password must be between 6 and 15 characters");
            valid = false;
        }

        if(repassword.isEmpty()) {
            etRePassword.setError("Re-type your password");
            valid = false;
        }

        if(repassword.equals(password)== false){
            etRePassword.setError("Passwords don't match");
            valid = false;
        }
        return valid;
    }

    public void initialize() {
        email = etEmail.getText().toString().trim();
        name = etName.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        repassword = etRePassword.getText().toString().trim();
    }


}