package com.subin.testapplication;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Neis {
    private String cityEducationCode;
    private String schoolCode;
    private String Date;
    private String schoolName;
    private final String apiKey = "8cedbf1c95ca4c9ea741727f04e7b6a3";

    public FoodInfo parseJsonFoodList(JSONObject obj) {
        JSONArray foodJson = null;
        FoodInfo info = new FoodInfo();
        try {
            foodJson = obj.getJSONArray("mealServiceDietInfo");
            JSONObject foodObj = foodJson.getJSONObject(1).getJSONArray("row").getJSONObject(0);
            info.setDate(foodObj.getString("MLSV_YMD"));
            info.setFoodList(foodObj.getString("DDISH_NM").replaceAll("<br/>","\n"));
            info.setSchoolName(foodObj.getString("SCHUL_NM"));
            return info;
        } catch (JSONException e) {
            e.printStackTrace();
            return info;
        }

    }
    public void getSchoolFoodList(Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Log.d("중간점","객체실행됨");
        HttpUrl neisUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("open.neis.go.kr")
                .addPathSegment("hub")
                .addPathSegment("mealServiceDietInfo")
                .addQueryParameter("KEY",apiKey)
                .addQueryParameter("Type","json")
                .addQueryParameter("ATPT_OFCDC_SC_CODE",cityEducationCode)
                .addQueryParameter("SD_SCHUL_CODE",schoolCode)
                .addQueryParameter("MLSV_YMD",Date)
                .build();
        Request request = new Request.Builder().url(neisUrl).get().build();
        client.newCall(request).enqueue(callback);

    }

    public void searchSchoolId(Callback callback) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("open.neis.go.kr")
                .addPathSegment("hub")
                .addPathSegment("schoolInfo")
                .addQueryParameter("KEY",apiKey)
                .addQueryParameter("Type","json")
                .addQueryParameter("SCHUL_NM",schoolName)
                .build();
        Request request = new Request.Builder().url(httpUrl).get().build();
        client.newCall(request).enqueue(callback);
    }
    public String getCityEducationCode() {
        return cityEducationCode;
    }

    public void setCityEducationCode(String cityEducationCode) {
        this.cityEducationCode = cityEducationCode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
