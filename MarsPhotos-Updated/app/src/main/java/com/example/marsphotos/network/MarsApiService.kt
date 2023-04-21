package com.example.marsphotos.network

import retrofit2.http.GET



// specify how Retrofit will talk to the backend server using HTTP requests
interface MarsApiService {
    // get photos string response from the backend server
    // specify the type of the HTTP request
    // complete URL: https://android-kotlin-fun-mars-server.appspot.com/photos
    @GET("photos") // "photos" -> the endpoint -> specifies a resource on the backend server
    // make this function a suspend function to allow it to run in a background thread (from within a CoroutineScope)
    suspend fun getPhotos() : List<MarsPhoto> // return type: URL that specifies where all the photos are
}
