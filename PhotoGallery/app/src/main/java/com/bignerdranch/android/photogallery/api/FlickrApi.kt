package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "b246d49ceed86708fafe77c28371cf06"
interface FlickrApi {
    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun fetchPhotos(): FlickrResponse
    @GET("services/rest?method=flickr.photos.search")
    suspend fun searchPhotos(@Query("text") query: String): FlickrResponse
}