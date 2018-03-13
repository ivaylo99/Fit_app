package com.example.ivo.fit_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    private EditText etEmail, etName, etUsername, etPassword, etRePassword;

    private TextView bLogin;

    private Button bRegister;

    private String email, name, username, password, repassword;
    private String preferences = "MyPrefs";

    private SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        settings = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        etEmail = (EditText) findViewById(R.id.etEmailReg);
        etName = (EditText) findViewById(R.id.etNameReg);
        etUsername = (EditText) findViewById(R.id.etUsernameReg);
        etPassword = (EditText) findViewById(R.id.etPasswordReg);
        etRePassword = (EditText) findViewById(R.id.etRePasswordReg);
        bRegister = (Button) findViewById(R.id.bRegisterReg);
        bLogin = (TextView) findViewById(R.id.bLoginReg);

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
        if (!validate()) {
            Toast.makeText(this, "sign up has failed", Toast.LENGTH_SHORT).show();
        } else {
            onSignupSucces();
        }
    }

    public void onSignupSucces() {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        final String email = etEmail.getText().toString();

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("email", email);
        editor.commit();

        Intent intention = new Intent(Register_Activity.this, User_Info_Activity.class);
        Register_Activity.this.startActivity(intention);
    }

    public boolean validate() {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 3 || name.length() > 20) {
            etName.setError("Enter name between 3 and 20 characters");
            valid = false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter valid email adress");
            valid = false;
        }

        if (username.isEmpty() || username.length() < 4 || username.length() > 12) {
            etUsername.setError("Username must be between 4 and 12 characters");
            valid = false;
        }

        if (password.isEmpty() || password.length() < 8 || password.length() > 15) {
            etPassword.setError("Password must be between 8 and 15 characters");
            valid = false;
        }

        if (repassword.isEmpty()) {
            etRePassword.setError("Re-type your password");
            valid = false;
        }

        if (repassword.equals(password) == false) {
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
