package com.example.bismillahbisa.service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiMain {
    private Retrofit retrofit;

    public JadwalRepository getApiJadwal() {
        String BASE_URL = "http://api.aladhan.com/";
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
        return retrofit.create(JadwalRepository.class);
    }
}