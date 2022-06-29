package com.subin.foodSchool;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {SettingsEntity.class},exportSchema = false)
public abstract class SettingsDB extends RoomDatabase {
    public abstract SettingsDAO dao();

}
