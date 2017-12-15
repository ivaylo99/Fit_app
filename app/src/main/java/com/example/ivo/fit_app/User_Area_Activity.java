package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class User_Area_Activity extends AppCompatActivity {

    MaterialBetterSpinner materialBetterSpinner1 ,materialBetterSpinner2 ,materialBetterSpinner3 ,materialBetterSpinner4 ;

    String[] s1 = new String[]{"Male", "Female"};
    String[] s2 = new String[]{"Clean bulk", "Gain muscle , lose fat"};
    String[] s3 = new String[]{"Deskt job with little exercise", "1-3 hrs/wk of light exercise", "3-5 hrs/wk of moderate exercise" ,"5-6 hrs/wk of strenuous exercise","7-21 hrs/wk of strenuous exercise"};
    String[] s4 = new String[]{"3 meals/day", "4 meals/day", "5 meals/day"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__area_);


        //get the spinner from the xml.
//create a list of items for the spinner.

//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.

        materialBetterSpinner1 = (MaterialBetterSpinner)findViewById(R.id.spinner);
        materialBetterSpinner2 = (MaterialBetterSpinner)findViewById(R.id.spinner1);
        materialBetterSpinner3 = (MaterialBetterSpinner)findViewById(R.id.spinner2);
        materialBetterSpinner4 = (MaterialBetterSpinner)findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(User_Area_Activity.this, android.R.layout.simple_dropdown_item_1line, s1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(User_Area_Activity.this, android.R.layout.simple_dropdown_item_1line, s2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(User_Area_Activity.this, android.R.layout.simple_dropdown_item_1line, s3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(User_Area_Activity.this, android.R.layout.simple_dropdown_item_1line, s4);

        materialBetterSpinner1.setAdapter(adapter1);
        materialBetterSpinner2.setAdapter(adapter2);
        materialBetterSpinner3.setAdapter(adapter3);
        materialBetterSpinner4.setAdapter(adapter4);

    }
}