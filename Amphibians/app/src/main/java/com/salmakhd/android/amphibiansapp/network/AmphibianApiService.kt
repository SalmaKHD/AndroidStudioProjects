package com.salmakhd.android.amphibiansapp.network

import retrofit2.http.GET

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}