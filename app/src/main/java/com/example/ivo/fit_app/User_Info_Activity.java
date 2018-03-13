package com.example.ivo.fit_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User_Info_Activity extends AppCompatActivity {

    private MaterialBetterSpinner materialBetterSpinner1, materialBetterSpinner2, materialBetterSpinner3, materialBetterSpinner4, materialBetterSpinner5, materialBetterSpinner6, materialBetterSpinner7;

    private Button btn;

    private String[] s1 = new String[]{"Male", "Female"};
    private String[] s2 = new String[]{"Clean bulk", "Gain muscle, Lose fat"};
    private String[] s3 = new String[]{"No activity", "3 workouts/week", "more than 3 workouts/week"};
    private String[] s4 = new String[]{"3 meals/day", "4 meals/day", "5 meals/day"};
    private String[] s5 = new String[83];
    private String[] s6 = new String[141];
    private String[] s7 = new String[86];

    private Integer weight, age, height, mealcount = 3, check = 0, ageValue = 18, weightValue = 40, heightValue = 130, counter;

    private double activity;

    private String gender, goal, username, password, email;
    private String preferences = "MyPrefs";

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_);

        settings = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        for (counter = 0; counter < 83; counter++) {
            s5[counter] = ageValue.toString();
            ageValue++;
        }

        for (counter = 0; counter < 141; counter++) {
            s6[counter] = weightValue.toString();
            weightValue++;
        }

        for (int counter = 0; counter < 86; counter++) {
            s7[counter] = heightValue.toString();
            heightValue++;
        }

        btn = (Button) findViewById(R.id.button);

        materialBetterSpinner1 = (MaterialBetterSpinner) findViewById(R.id.spinner1);
        materialBetterSpinner2 = (MaterialBetterSpinner) findViewById(R.id.spinner2);
        materialBetterSpinner3 = (MaterialBetterSpinner) findViewById(R.id.spinner3);
        materialBetterSpinner4 = (MaterialBetterSpinner) findViewById(R.id.spinner4);
        materialBetterSpinner5 = (MaterialBetterSpinner) findViewById(R.id.spinner5);
        materialBetterSpinner6 = (MaterialBetterSpinner) findViewById(R.id.spinner6);
        materialBetterSpinner7 = (MaterialBetterSpinner) findViewById(R.id.spinner7);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s6);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s7);

        materialBetterSpinner1.setAdapter(adapter1);
        materialBetterSpinner2.setAdapter(adapter2);
        materialBetterSpinner3.setAdapter(adapter3);
        materialBetterSpinner4.setAdapter(adapter4);
        materialBetterSpinner5.setAdapter(adapter5);
        materialBetterSpinner6.setAdapter(adapter6);
        materialBetterSpinner7.setAdapter(adapter7);

        materialBetterSpinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText1 = adapterView.getItemAtPosition(position).toString();

                if (mSelectedText1.equals("Male")) {
                    gender = "male";
                }
                if (mSelectedText1.equals("Female")) {
                    gender = "female";
                }
            }
        });

        materialBetterSpinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText2 = adapterView.getItemAtPosition(position).toString();

                if (mSelectedText2.equals("Clean bulk")) {
                    goal = "bulk";
                }
                if (mSelectedText2.equals("Gain muscle, Lose fat")) {
                    goal = "cut";
                }

            }
        });

        materialBetterSpinner3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText3 = adapterView.getItemAtPosition(position).toString();

                if (mSelectedText3.equals("No activity")) {
                    activity = 1.2;
                }
                if (mSelectedText3.equals("3 workouts/week")) {
                    activity = 1.3;
                }
                if (mSelectedText3.equals("more than 3 workouts/week")) {
                    activity = 1.4;
                }

            }
        });

        materialBetterSpinner4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText4 = adapterView.getItemAtPosition(position).toString();
                int mSelectedId = position;
                if (mSelectedText4.equals("3 meals/day")) {
                    mealcount = 3;
                }
                if (mSelectedText4.equals("4 meals/day")) {
                    mealcount = 4;
                }
                if (mSelectedText4.equals("5 meals/day")) {
                    mealcount = 5;
                }
            }
        });

        materialBetterSpinner5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText5 = adapterView.getItemAtPosition(position).toString();
                age = Integer.parseInt(mSelectedText5);

            }
        });

        materialBetterSpinner6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText6 = adapterView.getItemAtPosition(position).toString();
                weight = Integer.parseInt(mSelectedText6);
            }
        });

        materialBetterSpinner7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText7 = adapterView.getItemAtPosition(position).toString();
                height = Integer.parseInt(mSelectedText7);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(check == 0)
                //   Toast.makeText(getApplicationContext(),"opaa",Toast.LENGTH_SHORT).show();

                username = settings.getString("username", username);
                email = settings.getString("email", email);
                password = settings.getString("password", password);

                User user = new User(username, email, password, gender, height, weight, age, activity, goal, mealcount);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserRegisterService.ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserRegisterService client = retrofit.create(UserRegisterService.class);
                Call<User> userCall = client.createUser(user);

                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(User_Info_Activity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                            Intent login_intent = new Intent(User_Info_Activity.this, Login_Activity.class);
                            User_Info_Activity.this.startActivity(login_intent);
                        } else {
                            Toast.makeText(User_Info_Activity.this, "Unsuccessfull registration", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(User_Info_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(User_Info_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }//on create


}