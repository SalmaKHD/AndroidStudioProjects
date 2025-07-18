package com.salmakhd.android.shahabseries.instagram.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// use singleton pattern
public class RetrofitClient {
    private Retrofit retrofit;
    private static RetrofitClient retrofitClient = null;
    public static RetrofitClient getInstance() {
        if(retrofitClient==null) {
            retrofitClient = new RetrofitClient();

        }
        return retrofitClient;
    }
    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost/local/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Api getApi() {
        return retrofit.create(Api.class );
    }

}
