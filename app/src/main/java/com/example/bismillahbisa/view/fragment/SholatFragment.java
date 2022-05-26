package com.example.bismillahbisa.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bismillahbisa.R;
import com.example.bismillahbisa.adapter.SholatDiscoverAdapter;
import com.example.bismillahbisa.model.DataAdapter;
import com.example.bismillahbisa.model.sholat.DataItem;
import com.example.bismillahbisa.model.sholat.Gregorian;
import com.example.bismillahbisa.model.sholat.Hijri;
import com.example.bismillahbisa.model.sholat.SholatDiscoverResponse;
import com.example.bismillahbisa.model.sholat.Timings;
import com.example.bismillahbisa.service.ApiMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SholatFragment extends Fragment {
    TextView txtDateEn, txtDateHijri, txtLocation, txtTiming, txtClock;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sholat, container, false);
        txtDateEn = view.findViewById(R.id.txtDateEn);
        txtDateHijri = view.findViewById(R.id.txtDateHijri);
        txtLocation = view.findViewById(R.id.txtLocation);
        txtTiming = view.findViewById(R.id.txtTiming);
        txtClock = view.findViewById(R.id.txtClock);
        recyclerView = view.findViewById(R.id.recycleView);

        getJadwaL();
        return view;
    }

    void getJadwaL() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        final int day = cal.get(Calendar.DAY_OF_MONTH) - 1;
        final int hour = cal.get(Calendar.HOUR_OF_DAY);
        final int minute = cal.get(Calendar.MINUTE);
        ApiMain apiMain = new ApiMain();
        apiMain.getApiJadwal().getJadwalSholat(month, year).enqueue(new Callback<SholatDiscoverResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SholatDiscoverResponse> call, Response<SholatDiscoverResponse> response) {
                SholatDiscoverResponse res = response.body();
                Timings timings = res.getData().get(day).getTimings();
                Gregorian gregorian = res.getData().get(day).getDate().getGregorian();
                Hijri hijri = res.getData().get(day).getDate().getHijri();
                txtDateEn.setText(gregorian.getWeekday().getEn() + ", "
                        + gregorian.getDay() + " "
                        + gregorian.getMonth().getEn() + " "
                        + gregorian.getYear());
                txtDateHijri.setText(hijri.getDay() + " "
                        + hijri.getMonth().getEn() + " "
                        + hijri.getYear() + " "
                        + hijri.getDesignation().getAbbreviated());

                DataAdapter currentTime = closetHour(hour, minute, timings);
                txtTiming.setText(currentTime.getName());
                txtClock.setText(currentTime.getClock());
                List<DataAdapter> dataAdapter = new ArrayList<>();
                dataAdapter.add(new DataAdapter("Fajr", timings.getFajr()));
                dataAdapter.add(new DataAdapter("Dhuhr", timings.getDhuhr()));
                dataAdapter.add(new DataAdapter("Asr", timings.getAsr()));
                dataAdapter.add(new DataAdapter("Maghrib", timings.getMaghrib()));
                dataAdapter.add(new DataAdapter("Isha", timings.getIsha()));
                SholatDiscoverAdapter adapter = new SholatDiscoverAdapter();
                adapter.setData(dataAdapter);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<SholatDiscoverResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    DataAdapter closetHour(int hour, int minute, Timings timings) {
        DataAdapter dataAdapter = new DataAdapter();
        int distance = 0;
        hour = (hour * 60) + minute;

        int cdistance = parseHour(timings.getFajr()) - hour;
        if (cdistance > distance) {
            distance = cdistance;
            dataAdapter.setName("Fajr");
            dataAdapter.setClock(timings.getFajr());
        }

        cdistance = parseHour(timings.getDhuhr()) - hour;
        if (distance > 0) {
            if (cdistance < distance) {
                distance = cdistance;
                dataAdapter.setName("Dhuhr");
                dataAdapter.setClock(timings.getDhuhr());
            }
        } else if (cdistance > distance) {
            distance = cdistance;
            dataAdapter.setName("Dhuhr");
            dataAdapter.setClock(timings.getDhuhr());
        }

        cdistance = parseHour(timings.getAsr()) - hour;
        if (distance > 0) {
            if (cdistance < distance) {
                distance = cdistance;
                dataAdapter.setName("Asr");
                dataAdapter.setClock(timings.getAsr());
            }
        } else if (cdistance > distance) {
            distance = cdistance;
            dataAdapter.setName("Asr");
            dataAdapter.setClock(timings.getAsr());
        }

        cdistance = parseHour(timings.getMaghrib()) - hour;
        if (distance > 0) {
            if (cdistance < distance) {
                distance = cdistance;
                dataAdapter.setName("Maghrib");
                dataAdapter.setClock(timings.getMaghrib());
            }
        } else if (cdistance > distance) {
            distance = cdistance;
            dataAdapter.setName("Maghrib");
            dataAdapter.setClock(timings.getMaghrib());
        }

        cdistance = parseHour(timings.getIsha()) - hour;
        if (distance > 0) {
            if (cdistance < distance) {
                distance = cdistance;
                dataAdapter.setName("Isha");
                dataAdapter.setClock(timings.getIsha());
            }
        } else if (cdistance > distance) {
            distance = cdistance;
            dataAdapter.setName("Isha");
            dataAdapter.setClock(timings.getIsha());
        }

        if (distance == 0){
            dataAdapter.setName("Fajr");
            dataAdapter.setClock(timings.getFajr());
        }
        return dataAdapter;
    }

    int parseHour(String date) {
        int hour = Integer.parseInt(date.substring(0, 2));
        int minutes = Integer.parseInt(date.substring(3, 5));
        return (hour * 60) + minutes;
    }

}
