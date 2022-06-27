package com.subin.testapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings")
public class SettingsEntity {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "education_state_code")
    public String educationStateCode;

    @ColumnInfo(name = "school_code")
    public String schoolCode;


}
