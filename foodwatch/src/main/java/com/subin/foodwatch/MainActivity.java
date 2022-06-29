package com.subin.foodwatch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.subin.foodwatch.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends Activity  {

    private TextView TxtSchool;
    private TextView TxtDate;
    private TextView TxtFoodsList;
    private ActivityMainBinding binding;
    private Neis neis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TxtSchool= binding.School;
        neis = new Neis();
        TxtDate = binding.Date;
        TxtFoodsList = binding.foods;
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
                            TxtSchool.setText(foodData.getSchoolName());
                            TxtDate.setText(foodData.getDate());
                            TxtFoodsList.setText(foodData.getFoodList());
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
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                neis.setDate(format.format(date));
                neis.setSchoolCode("7310259");
                neis.setCityEducationCode("E10");
                neis.getSchoolFoodList(callback);
            }
        }).start();
    }


}