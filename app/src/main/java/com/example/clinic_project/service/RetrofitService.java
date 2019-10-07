package com.example.clinic_project.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

//    public static final String BASE_URL="http://128.199.180.50";
    public static final String BASE_URL="http://192.168.100.201:8001";


    public Retrofit getRetrofitService(){

        Retrofit getService=new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return getService;
    }
}

