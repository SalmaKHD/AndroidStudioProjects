package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer: AppContainer {
    // declare the base URL that will be used to access specific resources on the backend server
    private val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"
    // build a Retrofit object

    private val retrofit = Retrofit.Builder()
        // add a convertor which will convert JSON response to Kotlin objects
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        // add the base URI of the server -> domain name of the backend server
        .baseUrl(BASE_URL)
        // create the object
        .build()

    // since calls to the build() function on Retofit objects are expensive, create a singleton and
    // create a single Retrofit object once only
    private val retrofitService: MarsApiService by lazy {
        // create a retrofit object using the create() method and passing the API
        // Retrofit will use to communicate with the backend server (JSON responses
        // converted to Kotlin strings using the Retrofit Scalars library
        retrofit.create(com.example.marsphotos.network.MarsApiService::class.java)
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        DefaultMarsPhotosRepository(retrofitService)
    }
}
