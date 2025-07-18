package com.salmakhd.android.onlineshop.claases;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;
    private RetrofitClient() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl("http://nirvanaaa.ir/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static RetrofitClient getInstance() {
        if(retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }
    public RetrofitApi getCafeApi() {
        return retrofit.create(RetrofitApi.class);
    }
}
