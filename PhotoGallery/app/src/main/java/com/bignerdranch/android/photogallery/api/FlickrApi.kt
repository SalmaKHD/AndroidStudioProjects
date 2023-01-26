package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET
private const val API_KEY = "b246d49ceed86708fafe77c28371cf06"
interface FlickrApi {
    @GET("services/rest/?method=flickr.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    suspend fun fetchPhotos():FlickrResponse
}