package com.subin.foodwatch;

import android.app.Activity;
import android.os.Bundle;
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

public class MainActivity extends Activity implements DataClient.OnDataChangedListener {

    private TextView TxtSchool;
    private TextView TxtDate;
    private TextView TxtFoodsList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TxtSchool= binding.School;
        TxtDate = binding.Date;
        TxtFoodsList = binding.foods;
    }

    @Override
    public void onDataChanged(@NonNull DataEventBuffer dataEventBuffer) {
        for(DataEvent event : dataEventBuffer) {
            if(event.getType()==DataEvent.TYPE_CHANGED){
                DataItem item = event.getDataItem();
                if(item.getUri().getPath().compareTo("/foods")==0) {
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                   TxtSchool.setText(dataMap.getString("School"));
                   TxtDate.setText(dataMap.getString("Date"));
                   TxtFoodsList.setText(dataMap.getString("Foods"));
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Wearable.getDataClient(this).removeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Wearable.getDataClient(this).addListener(this);
    }
}