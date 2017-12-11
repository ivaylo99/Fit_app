package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class User_Area_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__area_);

        final  EditText etUsername = (EditText) findViewById(R.id.etUsername);


        //get the spinner from the xml.
        Spinner dropdown1 = (Spinner)findViewById(R.id.spinner);
        Spinner dropdown2 = (Spinner)findViewById(R.id.spinner2);
        Spinner dropdown3 = (Spinner)findViewById(R.id.spinner3);
        Spinner dropdown4 = (Spinner)findViewById(R.id.spinner4);
//create a list of items for the spinner.
        String[] s1 = new String[]{"Male", "Female"};
        String[] s2 = new String[]{"Clean bulk", "Gain muscle , lose fat"};
        String[] s3 = new String[]{"Deskt job with little exercise", "1-3 hrs/wk of light exercise", "3-5 hrs/wk of moderate exercise" ,"5-6 hrs/wk of strenuous exercise","7-21 hrs/wk of strenuous exercise"};
        String[] s4 = new String[]{"3 meals/day", "4 meals/day", "5 meals/day"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, s1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, s2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, s3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, s4);
//set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter1);
        dropdown2.setAdapter(adapter2);
        dropdown3.setAdapter(adapter3);
        dropdown4.setAdapter(adapter4);


    }
}