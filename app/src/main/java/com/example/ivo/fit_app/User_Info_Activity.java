package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class User_Info_Activity extends AppCompatActivity {

    MaterialBetterSpinner materialBetterSpinner1 ,materialBetterSpinner2 ,materialBetterSpinner3 ,materialBetterSpinner4 ,materialBetterSpinner5 ,materialBetterSpinner6 ,materialBetterSpinner7;
    Button btn;

    String[] s1 = new String[]{"Male", "Female"};
    String[] s2 = new String[]{"Clean bulk", "Gain muscle, Lose fat"};
    String[] s3 = new String[]{"No activity","3 workouts/week", "more than 3 workouts/week"};
    String[] s4 = new String[]{"3 meals/day", "4 meals/day", "5 meals/day"};
    String[] s5 = new String[]{"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
    String[] s6 = new String[]{"40 KG", "41 KG", "42 KG", "43 KG", "44 KG", "45 KG", "46 KG", "47 KG", "48 KG", "49 KG", "50 KG", "51 KG", "52 KG", "53 KG", "54 KG", "55 KG", "56 KG", "57 KG", "58 KG", "59 KG", "60 KG", "61 KG", "62 KG", "63 KG", "64 KG", "65 KG", "66 KG", "67 KG", "68 KG", "69 KG", "70 KG", "71 KG", "72 KG", "73 KG", "74 KG", "75 KG", "76 KG", "77 KG", "78 KG", "79 KG", "80 KG", "81 KG", "82 KG", "83 KG", "84 KG", "85 KG", "86 KG", "87 KG", "88 KG", "89 KG", "90 KG", "91 KG", "92 KG", "93 KG", "94 KG", "95 KG", "96 KG", "97 KG", "98 KG", "99 KG", "100 KG", "101 KG", "102 KG", "103 KG", "104 KG", "105 KG", "106 KG", "107 KG", "108 KG", "109 KG", "110 KG", "111 KG", "112 KG", "113 KG", "114 KG", "115 KG", "116 KG", "117 KG", "118 KG", "119 KG", "120 KG", "121 KG", "122 KG", "123 KG", "124 KG", "125 KG", "126 KG", "127 KG", "128 KG", "129 KG", "130 KG", "131 KG", "132 KG", "133 KG", "134 KG", "135 KG", "136 KG", "137 KG", "138 KG", "139 KG", "140 KG", "141 KG", "142 KG", "143 KG", "144 KG", "145 KG", "146 KG", "147 KG", "148 KG", "149 KG", "150 KG", "151 KG", "152 KG", "153 KG", "154 KG", "155 KG", "156 KG", "157 KG", "158 KG", "159 KG", "160 KG", "161 KG", "162 KG", "163 KG", "164 KG", "165 KG", "166 KG", "167 KG", "168 KG", "169 KG", "170 KG", "171 KG", "172 KG", "173 KG", "174 KG", "175 KG", "176 KG", "177 KG", "178 KG", "179 KG", "180 KG" };
    String[] s7 = new String[]{"130 CM", "131 CM", "132 CM", "133 CM", "134 CM", "135 CM", "136 CM", "137 CM", "138 CM", "139 CM", "140 CM", "141 CM", "142 CM", "143 CM", "144 CM", "145 CM", "146 CM", "147 CM", "148 CM", "149 CM", "150 CM", "151 CM", "152 CM", "153 CM", "154 CM", "155 CM", "156 CM", "157 CM", "158 CM", "159 CM", "160 CM", "161 CM", "162 CM", "163 CM", "164 CM", "165 CM", "166 CM", "167 CM", "168 CM", "169 CM", "170 CM", "171 CM", "172 CM", "173 CM", "174 CM", "175 CM", "176 CM", "177 CM", "178 CM", "179 CM", "180 CM", "181 CM", "182 CM", "183 CM", "184 CM", "185 CM", "186 CM", "187 CM", "188 CM", "189 CM", "190 CM", "191 CM", "192 CM", "193 CM", "194 CM", "195 CM", "196 CM", "197 CM", "198 CM", "199 CM", "200 CM", "201 CM", "202 CM", "203 CM", "204 CM", "205 CM", "206 CM", "207 CM", "208 CM", "209 CM", "210 CM", "211 CM", "212 CM", "213 CM", "214 CM", "215 CM"};
    Integer mealcount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_);
        btn = (Button) findViewById(R.id.button);

        //get the spinner from the xml.
//create a list of items for the spinner.

//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.

        materialBetterSpinner1 = (MaterialBetterSpinner)findViewById(R.id.spinner1);
        materialBetterSpinner2 = (MaterialBetterSpinner)findViewById(R.id.spinner2);
        materialBetterSpinner3 = (MaterialBetterSpinner)findViewById(R.id.spinner3);
        materialBetterSpinner4 = (MaterialBetterSpinner)findViewById(R.id.spinner4);
        materialBetterSpinner5 = (MaterialBetterSpinner)findViewById(R.id.spinner5);
        materialBetterSpinner6 = (MaterialBetterSpinner)findViewById(R.id.spinner6);
        materialBetterSpinner7 = (MaterialBetterSpinner)findViewById(R.id.spinner7);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s6);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(User_Info_Activity.this, android.R.layout.simple_dropdown_item_1line, s7);


        materialBetterSpinner1.setAdapter(adapter1);
        materialBetterSpinner2.setAdapter(adapter2);
        materialBetterSpinner3.setAdapter(adapter3);
        materialBetterSpinner4.setAdapter(adapter4);
        materialBetterSpinner5.setAdapter(adapter5);
        materialBetterSpinner6.setAdapter(adapter6);
        materialBetterSpinner7.setAdapter(adapter7);


        materialBetterSpinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText1 = adapterView.getItemAtPosition(position).toString();

            }
        });

        materialBetterSpinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText2 = adapterView.getItemAtPosition(position).toString();

            }
        });

        materialBetterSpinner3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText3 = adapterView.getItemAtPosition(position).toString();

            }
        });

        materialBetterSpinner4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText4 = adapterView.getItemAtPosition(position).toString();
                int mSelectedId = position;
                if (mSelectedText4.equals("3 meals/day")) {
                    mealcount = 1;
                }
                if (mSelectedText4.equals("4 meals/day")) {
                    mealcount = 2;
                }
                if (mSelectedText4.equals("5 meals/day")) {
                    mealcount = 3;
                }
            }
        });

        materialBetterSpinner5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText5 = adapterView.getItemAtPosition(position).toString();

            }
        });

        materialBetterSpinner6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText6 = adapterView.getItemAtPosition(position).toString();

            }
        });

        materialBetterSpinner7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String mSelectedText7 = adapterView.getItemAtPosition(position).toString();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mealcount == 1) {
                        Intent login_intent1 = new Intent(User_Info_Activity.this, User_Area_Activity.class);
                        User_Info_Activity.this.startActivity(login_intent1);
                }
                if (mealcount == 2) {
                    Intent login_intent1 = new Intent(User_Info_Activity.this, User_Area_Activity4.class);
                    User_Info_Activity.this.startActivity(login_intent1);
                }
                if (mealcount == 3) {
                    Intent login_intent1 = new Intent(User_Info_Activity.this, User_Area_Activity5.class);
                    User_Info_Activity.this.startActivity(login_intent1);
                }


            }
        });

    }

}