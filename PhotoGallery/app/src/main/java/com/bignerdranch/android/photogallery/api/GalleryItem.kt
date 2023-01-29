package com.bignerdranch.android.photogallery.api

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class GalleryItem (
    val title:String,
    val id:String,
    @Json(name="url_s") val url:String,
    // owner property needed for producing the full URL of Flicker images
    val owner: String
) {
    // generate a full URL for the image
    val photoPageUri: Uri
    get() = Uri.parse("https://www.flickr.com/photos/")
    .buildUpon()
    .appendPath(owner)
    .appendPath(id)
    .build()

}