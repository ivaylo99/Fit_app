package com.example.ivo.fit_app;

import android.content.Intent;
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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dynamic_Calories_Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private EditText etKg;
    private Button btn;
    private TextView tvDynamic;
    private String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_calories_);
        etKg = (EditText) findViewById(R.id.etKg);
        btn = (Button) findViewById(R.id.button2);
        tvDynamic = (TextView) findViewById(R.id.tvDynamic);


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
                        Intent eatActivity = new Intent(Dynamic_Calories_Activity.this, Dynamic_Calories_Activity.class);
                        startActivity(eatActivity);
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
               timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
                String kilograms = etKg.getText().toString().trim();
                tvDynamic.setText(timeStamp);
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
}