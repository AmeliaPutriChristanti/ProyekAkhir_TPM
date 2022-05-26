package com.example.bismillahbisa.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = com.example.bismillahbisa.entity.DataIbadah.class, version =1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract com.example.bismillahbisa.entity.DataIbadahDAO dao();

    private static AppDatabase appDatabase;
    public static AppDatabase inidb(Context context) {
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db_sholat").allowMainThreadQueries().build();
            return appDatabase;
        }
    }
