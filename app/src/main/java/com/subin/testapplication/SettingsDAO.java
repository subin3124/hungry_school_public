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

    @Update
    void UpdateSettings(SettingsEntity entity);
}
