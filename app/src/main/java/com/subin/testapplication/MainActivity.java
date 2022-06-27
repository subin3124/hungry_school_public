package com.subin.testapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    TextView textFood;
    TextView todayDate;
    TextView schoolName;
    Button clickBtn1;
    Button btnSettigns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickBtn1 = findViewById(R.id.clickBtn1);
        textFood = findViewById(R.id.textFood);
        todayDate = findViewById(R.id.todayDate);
        schoolName = findViewById(R.id.schoolName);
        btnSettigns = findViewById(R.id.configBtn);
        clickBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Neis neis = new Neis();
                Callback callback = new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        ResponseBody body = response.body();
                        try {
                            FoodInfo foodData = neis.parseJsonFoodList(new JSONObject(body.string()));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textFood.setText(foodData.getFoodList());
                                    todayDate.setText(foodData.getDate()+"\n");
                                    schoolName.setText(foodData.getSchoolName());
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                neis.setCityEducationCode("E10");
                neis.setSchoolCode("7310259");
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                neis.setDate(format.format(date));
                neis.getSchoolFoodList(callback);
            }
        });
        btnSettigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Setting.class));
            }
        });
    }
}