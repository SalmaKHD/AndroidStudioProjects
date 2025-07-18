package com.bignerdranch.android.photogallery

import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.GalleryItem
import com.bignerdranch.android.photogallery.api.PhotoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PhotoRepository {
    private val flickrApi:FlickrApi
    init {
        // create an OkHttpClient to alter the url sent in the request
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PhotoInterceptor())
            .build()

        // configure the Retrofit instance
        val retrofit = Retrofit.Builder()
                // use flickr Api page for retrieving photos
            .baseUrl("https://api.flickr.com/")
            /*
            Explanation:
            Retrofit will use the argument to convert the response of the request into a String
            original type: okHttp3.ResponseBody
             */
            // instruct Retrofit to use Moshi to convert Json response to Kotlin objects
            .addConverterFactory(MoshiConverterFactory.create())
                // add the client to HTTP requests
            .client(okHttpClient)
            .build()
        // create an instance that implements the interface
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    // fetch gallery items in a coroutine
    suspend fun fetchPhotos():List<GalleryItem> =
        flickrApi.fetchPhotos().photos.galleryItems
    suspend fun searchPhotos(query:String):List<GalleryItem> =
        flickrApi.searchPhotos(query).photos.galleryItems
}