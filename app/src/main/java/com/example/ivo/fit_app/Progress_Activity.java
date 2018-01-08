package com.example.ivo.fit_app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Progress_Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_);

        Button button = (Button) findViewById(R.id.button);
        img = (ImageView) findViewById(R.id.img);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //disable btn if user has no camera

        if (!hasCamera()) {
            button.setEnabled(false);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case (R.id.nav_account):
                        Intent accountActivity = new Intent(Progress_Activity.this, Login_Activity.class);
                        startActivity(accountActivity);
                        break;
                    case (R.id.nav_settings):
                        Intent settingsActivity = new Intent(Progress_Activity.this, Settings_Activity.class);
                        startActivity(settingsActivity);
                        break;
                    case (R.id.nav_progress):
                        Intent progressActivity = new Intent(Progress_Activity.this, Progress_Activity.class);
                        startActivity(progressActivity);
                        break;
                    case (R.id.nav_eat):
                        Intent eatActivity = new Intent(Progress_Activity.this, What_To_Eat_Activity.class);
                        startActivity(eatActivity);
                        break;
                    case (R.id.nav_logout):
                        Intent logoutActivity = new Intent(Progress_Activity.this, Login_Activity.class);
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

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //launching the camera

    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
            String currentTimeStamp = dateFormat.format(new Date());
            createDirectoryAndSaveFile(photo,currentTimeStamp.toString());
            img.setImageBitmap(photo);
        }
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {


        File direct = new File(Environment.getExternalStorageDirectory() + "/Fit_app");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Fit_app/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Fit_app/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}