package com.subin.testapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SettingsDAO {

    @Query("SELECT * FROM settings where id = 1")
    SettingsEntity getSettings();

    @Insert
    void InitializeSettings(SettingsEntity entity);

    @Query("update settings set education_state_code=:edustate, school_code=:schoolCode where id=1")
    void UpdateSettings(String edustate, String schoolCode);
}
