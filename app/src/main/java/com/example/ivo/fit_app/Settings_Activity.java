package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Settings_Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_);
         TextView txt = (TextView) findViewById(R.id.tv5);


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
                        Intent accountActivity = new Intent(Settings_Activity.this, Login_Activity.class);
                        startActivity(accountActivity);
                        break;
                    case(R.id.nav_settings):
                        Intent settingsActivity = new Intent(Settings_Activity.this, Settings_Activity.class);
                        startActivity(settingsActivity);
                        break;
                    case(R.id.nav_progress):
                        Intent progressActivity = new Intent(Settings_Activity.this, Progress_Activity.class);
                        startActivity(progressActivity);
                        break;
                    case(R.id.nav_eat):
                        Intent eatActivity = new Intent(Settings_Activity.this, Dynamic_Calories_Activity.class);
                        startActivity(eatActivity);
                        break;
                    case(R.id.nav_logout):
                        Intent logoutActivity = new Intent(Settings_Activity.this, Login_Activity.class);
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
}