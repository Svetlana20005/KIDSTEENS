package com.example.kidsteens;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static Retrofit instance = null;
    public static Retrofit getServer(){
        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.251:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
    private void RetroHelper(){}
}
