package com.example.bismillahbisa.view.fragment;


import android.os.AsyncTask;

import com.example.bismillahbisa.entity.AppDatabase;
import com.example.bismillahbisa.entity.DataIbadah;

import java.util.List;

public class MainPresenterCatatan implements MainContactCatatan.presenter {
    private MainContactCatatan.view view;

    public MainPresenterCatatan(MainContactCatatan.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long> {
        private AppDatabase appDatabase;
        private DataIbadah dataIbadah;
        public InsertData(AppDatabase appDatabase, DataIbadah dataIbadah) {
            this.appDatabase = appDatabase;
            this.dataIbadah = dataIbadah;
        }

        @Override
        protected Long doInBackground(Void... voids){
            return appDatabase.dao().insertData(dataIbadah);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }

    @Override
    public void insertData(String date, String fardhu, String sunah, String puasa, AppDatabase database) {
        final DataIbadah dataIbadah = new DataIbadah();
        dataIbadah.setDate(date);
        dataIbadah.setFardhu(fardhu);
        dataIbadah.setSunah(sunah);
        dataIbadah.setPuasa(puasa);
        new InsertData(database, dataIbadah).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataIbadah> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataIbadah dataIbadah;

        public EditData(AppDatabase appDatabase, DataIbadah dataIbadah) {
            this.appDatabase = appDatabase;
            this.dataIbadah = dataIbadah;
        }

        @Override
        protected Integer doInBackground(Void... voids){
            return appDatabase.dao().updateData(dataIbadah);
        }

        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            view.successAdd();
        }
    }
    @Override
    public void editData(String date, String fardhu, String sunah, String puasa, int id, AppDatabase database) {
        final DataIbadah dataIbadah = new DataIbadah();
        dataIbadah.setDate(date);
        dataIbadah.setFardhu(fardhu);
        dataIbadah.setSunah(sunah);
        dataIbadah.setPuasa(puasa);
        dataIbadah.setId(id);
        new EditData(database, dataIbadah).execute();
    }

    class DeleteData extends AsyncTask<Void,Void,Long> {
        private AppDatabase appDatabase;
        private DataIbadah dataIbadah;
        public DeleteData(AppDatabase appDatabase, DataIbadah dataIbadah) {
            this.appDatabase = appDatabase;
            this.dataIbadah = dataIbadah;
        }

        @Override
        protected Long doInBackground(Void... voids){
            appDatabase.dao().deleteData(dataIbadah);
            return null;
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }
    @Override
    public void deleteData(DataIbadah dataIbadah, AppDatabase database) {
        new DeleteData(database, dataIbadah).execute();
    }
}

