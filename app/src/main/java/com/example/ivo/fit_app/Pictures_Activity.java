package com.example.ivo.fit_app;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.ArrayList;

public class Pictures_Activity extends AppCompatActivity {

    String path = Environment.getExternalStorageDirectory() + "/Pictures/Fit_app/";
    File file = new File (path);

    ArrayList<String> f = new ArrayList<String>();// list of file paths
    File[] listFile;


    Button prev, next;
    ImageSwitcher imgSwitcher;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    int i = 0;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures_);
      //  Ringtone r = RingtoneManager.getRingtone(this,uri);

        if (file.isDirectory()) {
            listFile = file.listFiles();

            for (int i = 0; i < listFile.length; i++) {

                f.add(listFile[i].getAbsolutePath());
            }
        }

       // Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        final TextView tv = (TextView) findViewById(R.id.textView5);

        Uri uri = Uri.fromFile(listFile[0]);


        imgSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);

        imgSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(
                        new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        Animation fadein = new AlphaAnimation(0, 1);
        fadein.setInterpolator(new DecelerateInterpolator());
        fadein.setDuration(1000);

        Animation fadeout = new AlphaAnimation(0, 1);
        fadeout.setInterpolator(new AccelerateInterpolator());
        fadeout.setDuration(1000);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadein);
        animation.addAnimation(fadeout);
        imgSwitcher.setAnimation(animation);


        prev = (Button) findViewById(R.id.btnPicPrev);
        next = (Button) findViewById(R.id.btnPicNext);

        name = listFile[0].getName();
        name = name.substring(0,10);
        tv.setText(name);

        imgSwitcher.setImageURI(uri);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    imgSwitcher.setImageURI(Uri.fromFile(listFile[i]));
                    name = listFile[i].getName();
                    name = name.substring(0,10);
                    tv.setText(name);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < listFile.length - 1) {
                    i++;
                    imgSwitcher.setImageURI(Uri.fromFile(listFile[i]));
                    name = listFile[i].getName();
                    name = name.substring(0,10);
                    tv.setText(name);
                }
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);
        View hview = mNavigationView.getHeaderView(0);
        TextView nav_user = (TextView)hview.findViewById(R.id.tvNav);
        nav_user.setText("opala");

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case (R.id.nav_account):
                        Intent accountActivity = new Intent(Pictures_Activity.this, Login_Activity.class);
                        startActivity(accountActivity);
                        break;
                    case (R.id.nav_settings):
                        Intent settingsActivity = new Intent(Pictures_Activity.this, Settings_Activity.class);
                        startActivity(settingsActivity);
                        break;
                    case (R.id.nav_progress):
                        Intent progressActivity = new Intent(Pictures_Activity.this, Progress_Activity.class);
                        startActivity(progressActivity);
                        break;
                    case (R.id.nav_eat):
                        Intent eatActivity = new Intent(Pictures_Activity.this, Dynamic_Calories_Activity.class);
                        startActivity(eatActivity);
                        //User_Area_Activity word = new User_Area_Activity();
                        break;
                    case (R.id.nav_logout):
                        Intent logoutActivity = new Intent(Pictures_Activity.this, Login_Activity.class);
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



}
