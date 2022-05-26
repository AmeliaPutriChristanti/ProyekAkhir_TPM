package com.example.bismillahbisa.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dbsholat")
public class DataIbadah {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "fardhu")
    private String fardhu;
    @ColumnInfo(name = "sunah")
    private String sunah;
    @ColumnInfo(name = "puasa")
    private String puasa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFardhu() {
        return fardhu;
    }

    public void setFardhu(String fardhu) {
        this.fardhu = fardhu;
    }

    public String getSunah() {
        return sunah;
    }

    public void setSunah(String sunah) {
        this.sunah = sunah;
    }

    public String getPuasa() {
        return puasa;
    }

    public void setPuasa(String puasa) {
        this.puasa = puasa;
    }

    }

