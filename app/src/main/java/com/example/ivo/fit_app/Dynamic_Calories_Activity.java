package com.example.ivo.fit_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dynamic_Calories_Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private EditText etWeight,etBicep,etChest,etWaist,etHip,etThigh,etCalf;
    private Button btn;
    private String username,id,token,weight,biceps,thigh,calf,hip,chest,waist;
    String preferences = "MyPrefs";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_calories_);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etBicep = (EditText) findViewById(R.id.etBicep);
        etCalf = (EditText) findViewById(R.id.etCalf);
        etChest = (EditText) findViewById(R.id.etChest);
        etHip = (EditText) findViewById(R.id.etHip);
        etThigh = (EditText) findViewById(R.id.etThigh);
        etWaist = (EditText) findViewById(R.id.etWaist);

        btn = (Button) findViewById(R.id.btnDynamic);

        settings = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this , mDrawerLayout , R.string.open , R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);

        View headerView = mNavigationView.getHeaderView(0);
        TextView nav_user = (TextView) headerView.findViewById(R.id.tvNav);
        username = settings.getString("username", username);
        nav_user.setText("Hello, " + username);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
                switch (menuItem.getItemId()){
                    case(R.id.nav_account):
                        Intent accountActivity = new Intent(Dynamic_Calories_Activity.this, Login_Activity.class);
                        startActivity(accountActivity);
                        break;
                    case(R.id.nav_settings):
                        Intent settingsActivity = new Intent(Dynamic_Calories_Activity.this, Settings_Activity.class);
                        startActivity(settingsActivity);
                        break;
                    case(R.id.nav_progress):
                        Intent progressActivity = new Intent(Dynamic_Calories_Activity.this, Progress_Activity.class);
                        startActivity(progressActivity);
                        break;
                    case(R.id.nav_eat):
                        Intent DynamicCalActivity = new Intent(Dynamic_Calories_Activity.this, Dynamic_Calories_Activity.class);
                        startActivity(DynamicCalActivity);
                        break;
                    case(R.id.nav_logout):
                        Intent logoutActivity = new Intent(Dynamic_Calories_Activity.this, Login_Activity.class);
                        startActivity(logoutActivity);
                        break;

                }
                return true;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = etWeight.getText().toString().trim();
                biceps = etBicep.getText().toString().trim();
                calf = etCalf.getText().toString().trim();
                chest = etChest.getText().toString().trim();
                hip = etHip.getText().toString().trim();
                thigh = etThigh.getText().toString().trim();
                waist = etWaist.getText().toString().trim();


                Measurements measurments = new Measurements(
                        Float.parseFloat(weight),Float.parseFloat(biceps),Float.parseFloat(calf),
                        Float.parseFloat(chest),Float.parseFloat(hip),Float.parseFloat(thigh),
                        Float.parseFloat(waist));

                token = settings.getString("token", token);
                id = settings.getString("id", id);

                addMeasurements(measurments,id,token);

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

    public  void addMeasurements(Measurements measurments, String id, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserRegisterService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AddMeasurementsService client = retrofit.create(AddMeasurementsService.class);
        Call<Measurements> userCall = client.addMeasurement(measurments,id,token);


        userCall.enqueue(new Callback<Measurements>() {
            @Override
            public void onResponse(Call<Measurements> call, Response<Measurements> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Dynamic_Calories_Activity.this, "success", Toast.LENGTH_SHORT).show();
                    // todo display the data instead of just a toast
                }
                else {
                    Toast.makeText(Dynamic_Calories_Activity.this, "Network problems", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Measurements> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(Dynamic_Calories_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(Dynamic_Calories_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }


}