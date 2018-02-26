package com.example.ivo.fit_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User_Area_Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private String token, id, calories,username;
    private Integer meals;
    private TextView tvHead;
    String preferences = "MyPrefs";
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area_);

        settings = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        tvHead = (TextView) findViewById(R.id.tvUaHead);
        tvHead.setBackgroundColor(0xAA000000);

        token = settings.getString("token", token);

        getIdAndCalories(token);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);

        View headerView = mNavigationView.getHeaderView(0);
        TextView nav_user = (TextView) headerView.findViewById(R.id.tvNav);
        username = settings.getString("username", username);
        nav_user.setText("Hello, " + username);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case (R.id.nav_account):
                        Intent accountActivity = new Intent(User_Area_Activity.this, Login_Activity.class);
                        startActivity(accountActivity);
                        break;
                    case (R.id.nav_settings):
                        Intent settingsActivity = new Intent(User_Area_Activity.this, Settings_Activity.class);
                        startActivity(settingsActivity);
                        break;
                    case (R.id.nav_progress):
                        Intent progressActivity = new Intent(User_Area_Activity.this, Progress_Activity.class);
                        startActivity(progressActivity);
                        break;
                    case (R.id.nav_eat):
                        Intent DynamicCalActivity = new Intent(User_Area_Activity.this, Dynamic_Calories_Activity.class);
                        startActivity(DynamicCalActivity);
                        break;
                    case (R.id.nav_logout):
                        Intent logoutActivity = new Intent(User_Area_Activity.this, Login_Activity.class);
                        startActivity(logoutActivity);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void getIdAndCalories(final String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetIdFromTokenService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetIdFromTokenService client = retrofit.create(GetIdFromTokenService.class);
        Call<User> userCall = client.getUserId(token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {

                    id = response.body().getId().toString();

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("id",id);
                    editor.commit();

                    calories = response.body().getCalories().toString();
                    tvHead.setText("Your daily calories: " + calories);

                    meals = response.body().getMeals();

                    getMeals(token, meals, id);

                } else {
                    Toast.makeText(User_Area_Activity.this, "Cant get id", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(User_Area_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                } else {
                    Toast.makeText(User_Area_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }



    public void getMeals(String token, final Integer meals, String id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetMealsService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetMealsService client = retrofit.create(GetMealsService.class);
        Call<List<Food>> userCall = client.getMeals(id,token);

        userCall.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (response.isSuccessful()) {

                    List<Food> list = response.body();
                    setLayout(list,meals);

                } else {
                    Toast.makeText(User_Area_Activity.this, "Cant get id", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(User_Area_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                } else {
                    Toast.makeText(User_Area_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }


    public void setLayout(List<Food> list , Integer meals) {

        Integer i = 0;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_exampla);

        for (; i < meals; i++) {
            TextView textView1 = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.gravity = Gravity.CENTER;
            layoutParams.setMargins(0, 30, 0, 30); // (left, top, right, bottom)
            textView1.setLayoutParams(layoutParams);
            textView1.setText("Meal " + (i + 1));
            textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView1.setBackgroundColor(0xAA000000); // hex color 0xAARRGGBB
            textView1.setTextSize(24);

            linearLayout.addView(textView1);

            // Add textview 2
            for (int l = 0; l < 3; l++) {

                TextView textView2 = new TextView(this);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.gravity = Gravity.CENTER;
                textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                layoutParams.setMargins(0, 50, 0, 30); // (left, top, right, bottom)
                textView2.setLayoutParams(layoutParams1);
                if(i == 0) {
                    textView2.setText(list.get(l).getName() + "   " + list.get(l).getAmount());
                }else if(i == 1) {
                    textView2.setText(list.get(l+3).getName() + "   " + list.get(l + 3).getAmount());
                }else if(i == 2){
                    textView2.setText(list.get(l+6).getName() + "   " + list.get(l+6).getAmount());
                }else if(i == 3){
                    textView2.setText(list.get(l+9).getName() + "   " + list.get(l+9).getAmount());
                }else {
                    textView2.setText(list.get(l+12).getName() + "   " + list.get(l+12).getAmount());
                }

                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                textView2.setBackgroundColor(0xAA000000); // hex color 0xAARRGGBB

                linearLayout.addView(textView2);
            }
        }
    }

}