package com.example.marsphotos.network

import retrofit2.Retrofit
import retrofit2.http.GET

// declare the base URL that will be used to access specific resources on the backend server
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
// build a Retrofit object
private val retrofit = Retrofit.Builder()
    // add a convertor which will convert JSON response to Kotlin objects
    .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json"))
    // add the base URI of the server -> domain name of the backend server
    .baseUrl(BASE_URL)
    // create the object
    .build()

// specify how Retrofit will talk to the backend server using HTTP requests
interface MarsApiService {
    // get photos string response from the backend server
    // specify the type of the HTTP request
    // complete URL: https://android-kotlin-fun-mars-server.appspot.com/photos
    @GET("photos") // "photos" -> the endpoint -> specifies a resource on the backend server
    // make this function a suspend function to allow it to run in a background thread (from within a CoroutineScope)
    suspend fun getPhotos() : List<MarsPhoto> // return type: URL that specifies where all the photos are
}

// since calls to the build() function on Retofit objects are expensive, create a singleton and
// create a single Retrofit object once only
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        // create a retrofit object using the create() method and passing the API
        // Retrofit will use to communicate with the backend server (JSON responses
        // converted to Kotlin strings using the Retrofit Scalars library
        retrofit.create(MarsApiService::class.java)
    }
}
