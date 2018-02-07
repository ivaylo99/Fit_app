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

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
             //   register();

                User user = new User("asdsdvasilevgikurti","asadsdlfata@gmail.com","gsadsdolemecatedas");


                final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserClient.ENDPOINT)
                        .client(okHttpClient)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserClient client = retrofit.create(UserClient.class);
                Call<User> userCall = client.createUser(user);


                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Register_Activity.this, "server returned data", Toast.LENGTH_SHORT).show();
                            // todo display the data instead of just a toast
                        }
                        else {
                            Toast.makeText(Register_Activity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(Register_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                            // logging probably not necessary
                        }
                        else {
                            Toast.makeText(Register_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                            // todo log to some central bug tracking service
                        }
                    }
                });

              //  sendNetworkRequest(user);
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

    private void sendNetworkRequest(User user) {

       Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

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
                Intent register_intent = new Intent(Register_Activity.this, User_Info_Activity.class);
                Register_Activity.this.startActivity(register_intent);
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