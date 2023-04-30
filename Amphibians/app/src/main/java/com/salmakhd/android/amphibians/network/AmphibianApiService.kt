package com.salmakhd.android.amphibians.network

import retrofit2.http.GET

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}