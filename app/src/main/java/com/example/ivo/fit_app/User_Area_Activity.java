package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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

public class User_Area_Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private String token;
    private Intent intention;
    private Bundle bundle;
    private Integer meals = 5, calories = 2750;
    private TextView tvHead;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_area_);

            tvHead = (TextView) findViewById(R.id.tvUaHead);
            tvHead.setText("Your daily calories: " + calories);
            tvHead.setBackgroundColor(0xAA000000);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_exampla);

        // Add textview 1
        for(Integer i = 0 ; i < meals ; i++ ) {
            TextView textView1 = new TextView(this);
            String str = i.toString();
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
            for(int l = 0 ; l < 3 ; l++) {

                TextView textView2 = new TextView(this);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.gravity = Gravity.CENTER;
                textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                layoutParams.setMargins(0,50,0,30); // (left, top, right, bottom)
                textView2.setLayoutParams(layoutParams1);
                textView2.setText("food " + (l + 1) + "of Meal" + (i + 1));
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                textView2.setBackgroundColor(0xAA000000); // hex color 0xAARRGGBB

                linearLayout.addView(textView2);
            }
        }



        bundle = getIntent().getExtras();
        intention = new Intent(User_Area_Activity.this,Dynamic_Calories_Activity.class);

        token = bundle.getString("token");
        intention.putExtra("token",token);
        Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this , mDrawerLayout , R.string.open , R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
                switch (menuItem.getItemId()){
                    case(R.id.nav_account):
                        Intent accountActivity = new Intent(User_Area_Activity.this, Login_Activity.class);
                        startActivity(accountActivity);
                        break;
                    case(R.id.nav_settings):
                        Intent settingsActivity = new Intent(User_Area_Activity.this, Settings_Activity.class);
                        startActivity(settingsActivity);
                        break;
                    case(R.id.nav_progress):
                        Intent progressActivity = new Intent(User_Area_Activity.this, Progress_Activity.class);
                        startActivity(progressActivity);
                        break;
                    case(R.id.nav_eat):
                        requestGetId(token);
                        break;
                    case(R.id.nav_logout):
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

        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public  void requestGetId(String token) {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserRegisterService.ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetIdFromTokenService client = retrofit.create(GetIdFromTokenService.class);
        Call<ResponseBody> userCall = client.getUserId(token);


        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    String id = null;
                    try {
                        id = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    intention.putExtra("id", id);

                    User_Area_Activity.this.startActivity(intention);

                   // Toast.makeText(User_Area_Activity.this, id , Toast.LENGTH_SHORT).show();
                    // todo display the data instead of just a toast
                }
                else {
                    Toast.makeText(User_Area_Activity.this, "Cant get id", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(User_Area_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(User_Area_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }


}