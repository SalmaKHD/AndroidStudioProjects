package com.salmakhd.android.onlineshop.claases;

import com.salmakhd.android.onlineshop.model.CafeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("coffee-shop/cafe-items-server.php")
    public Call<List<CafeItem>> getCafeItems(
            @Query("type") String type
    );
}