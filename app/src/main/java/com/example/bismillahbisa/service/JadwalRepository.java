package com.example.bismillahbisa.service;

import com.example.bismillahbisa.model.sholat.SholatDiscoverResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JadwalRepository {

    @GET("v1/calendarByAddress?address=yogyakarta&method=2")
    Call<SholatDiscoverResponse> getJadwalSholat(@Query("month") int month, @Query("year") int year);
}
