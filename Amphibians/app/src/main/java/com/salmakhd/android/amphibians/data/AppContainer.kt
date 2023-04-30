package com.salmakhd.android.amphibians.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.salmakhd.android.amphibians.network.AmphibianApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibianRepository: AmphibianRepository
}

class DefaultAppContainer: AppContainer {
    // create retrofit instance
private val BASE_URL ="https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }

    // DI implementation for AmphibianRepository
    override val amphibianRepository: AmphibianRepository by lazy {
        DefaultAmphibianRepository(retrofitService)
    }
}
