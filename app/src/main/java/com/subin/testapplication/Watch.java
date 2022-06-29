package com.subin.testapplication;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;

public class Watch {

    private DataClient dataClient;
    public void OnChangedFoods(FoodInfo foodInfo) {
        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create("/foods");
        putDataMapRequest.getDataMap().putString("School",foodInfo.getSchoolName());
        putDataMapRequest.getDataMap().putString("Date",foodInfo.getDate());
        putDataMapRequest.getDataMap().putString("Foods",foodInfo.getFoodList());
        PutDataRequest request = putDataMapRequest.asPutDataRequest();
        Task<DataItem> dataItemTask = dataClient.putDataItem(request);
    }
}
