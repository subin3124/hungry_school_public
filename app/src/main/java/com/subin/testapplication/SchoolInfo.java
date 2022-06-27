package com.subin.testapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SchoolInfo {
    private String region;
    private String schoolName;
    private String regionCode;
    private String schoolId;
    private String homePage;
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public List<SchoolInfo> getSchoolLists(String json) {
        List<SchoolInfo> list = new ArrayList<>();
        try {
            JSONArray SchoolsArray = new JSONObject(json).getJSONArray("schoolInfo").getJSONObject(1).getJSONArray("row");

            for(int i=0; i<SchoolsArray.length();i++){
                JSONObject School = SchoolsArray.getJSONObject(i);
                SchoolInfo info = new SchoolInfo();
                info.setSchoolId(School.getString("SD_SCHUL_CODE"));
                info.setSchoolName(School.getString("SCHUL_NM"));
                info.setRegion(School.getString("ATPT_OFCDC_SC_NM"));
                info.setHomePage(School.getString("HMPG_ADRES"));
                info.setRegionCode(School.getString("ATPT_OFCDC_SC_CODE"));
                list.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
