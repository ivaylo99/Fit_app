package com.example.ivo.fit_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User_Info_Activity extends AppCompatActivity {

    MaterialBetterSpinner materialBetterSpinner1 ,materialBetterSpinner2 ,materialBetterSpinner3 ,materialBetterSpinner4 ,materialBetterSpinner5 ,materialBetterSpinner6 ,materialBetterSpinner7;
    Button btn;

    String[] s1 = new String[]{"Male", "Female"};
    String[] s2 = new String[]{"Clean bulk", "Gain muscle, Lose fat"};
    String[] s3 = new String[]{"No activity","3 workouts/week", "more than 3 workouts/week"};
    String[] s4 = new String[]{"3 meals/day", "4 meals/day", "5 meals/day"};
    String[] s5 = new String[]{"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
    String[] s6 = new String[]{"40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180" };
    String[] s7 = new String[]{"130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215"};
    Integer mealcount = 0;

    private String gender="male" , goal= "bulk";
    private Integer weight= 80, age= 25, height= 150, activity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_);
        btn = (Button) findViewById(R.id.button);

        final Bundle bundle ;
        bundle = getIntent().getExtras();

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

                User user = new User(bundle.getString("username"),bundle.getString("email"),bundle.getString("password"),gender,height,weight,age,activity,goal);

                final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserClient.ENDPOINT)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserClient client = retrofit.create(UserClient.class);
                Call<User> userCall = client.createUser(user);


                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(User_Info_Activity.this, "server returned data", Toast.LENGTH_SHORT).show();
                            // todo display the data instead of just a toast
                        }
                        else {
                            Toast.makeText(User_Info_Activity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(User_Info_Activity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                            // logging probably not necessary
                        }
                        else {
                            Toast.makeText(User_Info_Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                            // todo log to some central bug tracking service
                        }
                    }
                });

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

    }//on create




}