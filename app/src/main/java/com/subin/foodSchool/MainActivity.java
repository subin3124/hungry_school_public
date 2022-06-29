package com.subin.foodSchool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.subin.testapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
                                    Watch watch = new Watch();
                                    watch.OnChangedFoods(getApplicationContext(),foodData);
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SettingsDB db = Room.databaseBuilder(getApplicationContext(), SettingsDB.class, "settings").build();
                        SettingsDAO dao = db.dao();
                        if(dao.getSettings()!=null){
                            neis.setCityEducationCode(dao.getSettings().educationStateCode);
                            neis.setSchoolCode(dao.getSettings().schoolCode);
                            Date date = new Date();
                            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                            neis.setDate(format.format(date));
                            neis.getSchoolFoodList(callback);
                        }else{
                            startActivity(new Intent(getApplicationContext(),Setting.class));
                        }
                    }
                }).start();




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