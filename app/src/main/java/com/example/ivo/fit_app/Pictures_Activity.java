package com.example.ivo.fit_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private Button prev, next;
    private ImageSwitcher imgSwitcher;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private Integer counter = 0;

    private String name, username;
    private String preferences = "MyPrefs";

    private SharedPreferences settings;

    private TextView dateView;

    String path = Environment.getExternalStorageDirectory() + "/Pictures/Fit_app/";
    File file = new File(path);

    ArrayList<String> f = new ArrayList<String>();// list of file paths
    File[] listFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures_);

        settings = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        if (file.isDirectory()) {
            listFile = file.listFiles();

            for (int counter = 0; counter < listFile.length; counter++) {

                f.add(listFile[counter].getAbsolutePath());
            }
        }

        dateView = (TextView) findViewById(R.id.dateView);

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
        dateView.setText(formatName(name));

        imgSwitcher.setImageURI(uri);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter > 0) {
                    counter--;
                    imgSwitcher.setImageURI(Uri.fromFile(listFile[counter]));
                    name = listFile[counter].getName();
                    dateView.setText(formatName(name));
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < listFile.length - 1) {
                    counter++;
                    imgSwitcher.setImageURI(Uri.fromFile(listFile[counter]));
                    name = listFile[counter].getName();
                    dateView.setText(formatName(name));
                }
            }
        });

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
                        Intent accountActivity = new Intent(Pictures_Activity.this, User_Area_Activity.class);
                        startActivity(accountActivity);
                        break;
                    case (R.id.nav_progress):
                        Intent progressActivity = new Intent(Pictures_Activity.this, Progress_Activity.class);
                        startActivity(progressActivity);
                        break;
                    case (R.id.nav_eat):
                        Intent DynamicCalActivity = new Intent(Pictures_Activity.this, Dynamic_Calories_Activity.class);
                        startActivity(DynamicCalActivity);
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

    private String formatName(String name) {
        name = name.substring(0, 10);
        return name;
    }

}
