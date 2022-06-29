package com.subin.foodSchool;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings")
public class SettingsEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "education_state_code")
    public String educationStateCode="";

    @ColumnInfo(name = "school_code")
    public String schoolCode="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEducationStateCode() {
        return educationStateCode;
    }

    public void setEducationStateCode(String educationStateCode) {
        this.educationStateCode = educationStateCode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
}
