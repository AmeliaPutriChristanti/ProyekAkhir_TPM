package com.example.bismillahbisa.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataIbadahDAO {
    @Insert
    Long insertData(DataIbadah dataIbadah);

    @Query("SELECT * FROM dbsholat")
    List<DataIbadah> getData();

    @Update
    int updateData(DataIbadah item);

    @Delete
    void deleteData(DataIbadah item);
}
