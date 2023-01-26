package com.bignerdranch.android.photogallery

import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PhotoRepository {
    private val flickrApi:FlickrApi
    init {
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
            .build()
        // create an instance that implements the interface
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    // fetch gallery items in a coroutine
    suspend fun fetchPhotos():List<GalleryItem> = flickrApi.fetchPhotos().photos.galleryItems
}