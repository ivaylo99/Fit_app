package com.example.ivo.fit_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String preferences = "MyPrefs";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        settings = getSharedPreferences(preferences, Context.MODE_PRIVATE);

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

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("username",username);
                editor.commit();

                password = etPassword.getText().toString();

                request(username,password);
            }
        });
    }



    public void request(final String username, final String password) {

        Login login = new Login(username,password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserLoginService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserLoginService client = retrofit.create(UserLoginService.class);
        Call<ResponseBody> userCall = client.getAuthorization(login);

        userCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    token = response.headers().get("authorization");
                    SharedPreferences settings = getSharedPreferences(preferences, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("token",token);
                    editor.commit();

                    Intent intention = new Intent(Login_Activity.this,User_Area_Activity.class);
                    Login_Activity.this.startActivity(intention);

                }
                else {
                    Toast.makeText(Login_Activity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(Login_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }
}