package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_Activity extends AppCompatActivity {
    private String username;
    private String password;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        final EditText etUsername = (EditText) findViewById(R.id.etUsernameLogin);
        final EditText etPassword = (EditText) findViewById(R.id.etPasswordLogin);
        final Button bLogin = (Button) findViewById(R.id.btnLoginLogin);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegisterLogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(Login_Activity.this, Register_Activity.class);
                Login_Activity.this.startActivity(register_intent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 username = etUsername.getText().toString();
                 password = etPassword.getText().toString();

                Login login = new Login(username,password);

                final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserLogin.ENDPOINT)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserLogin client = retrofit.create(UserLogin.class);
                Call<ResponseBody> userCall = client.getAuthorization(login);


                userCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            token = response.headers().get("authorization");
                            Toast.makeText(Login_Activity.this, token, Toast.LENGTH_SHORT).show();
                            // todo display the data instead of just a toast
                        }
                        else {
                            Toast.makeText(Login_Activity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(Login_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                            // logging probably not necessary
                        }
                        else {
                            Toast.makeText(Login_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                            // todo log to some central bug tracking service
                        }
                    }
                });
                Intent login_intent = new Intent(Login_Activity.this, User_Area_Activity.class);
               Login_Activity.this.startActivity(login_intent);
            }
        });
    }
}