package com.example.bismillahbisa.view.fragment;

import android.view.View;

import com.example.bismillahbisa.entity.AppDatabase;
import com.example.bismillahbisa.entity.DataIbadah;

import java.util.List;

public interface MainContactCatatan {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataIbadah> list);
        void editData(DataIbadah item);
        void deleteData(DataIbadah item);
    }

    interface presenter{
        void insertData(String date, String fardhu, String sunah, String puasa, AppDatabase appDatabase);
        void readData(AppDatabase database);
        void editData(String date, String fardhu, String sunah, String puasa, int id, AppDatabase database);
        void deleteData(DataIbadah dataIbadah, AppDatabase database);

    }
}

