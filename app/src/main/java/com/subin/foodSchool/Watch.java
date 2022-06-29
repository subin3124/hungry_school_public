package com.subin.foodSchool;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

public class Watch {

    private DataClient dataClient;
    public void OnChangedFoods(Context context, FoodInfo foodInfo) {
        dataClient = Wearable.getDataClient(context);
        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create("/foods");
        putDataMapRequest.getDataMap().putString("School",foodInfo.getSchoolName());
        putDataMapRequest.getDataMap().putString("Date",foodInfo.getDate());
        putDataMapRequest.getDataMap().putString("Foods",foodInfo.getFoodList());
        PutDataRequest request = putDataMapRequest.asPutDataRequest();
        request.setUrgent();
        Task<DataItem> dataItemTask = dataClient.putDataItem(request);

    }
}
