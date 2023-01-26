package com.bignerdranch.android.photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.photogallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"
class PhotoGalleryViewModel: ViewModel() {
    private val photoRepository:PhotoRepository = PhotoRepository()
    private val _galleryItems:MutableStateFlow<List<GalleryItem>> = MutableStateFlow(emptyList())
    val galleryItems:StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init{
        // call the fetchContents() function to issue a web request in a coroutine scope
        viewModelScope.launch {
            // catch possible errors Flickr may throw
            try {
                val items = fetchGalleryItems("flower")
                _galleryItems.value = items
            } catch (ex:Exception) {
                Log.e(TAG, "failed to fetch gallery items", ex)
            }
        }
    }

    // update images stream based on the passed query
    fun setQuery(query: String) {
        viewModelScope.launch { _galleryItems.value = fetchGalleryItems(query) }
    }

    // fetch images based on a query
    private suspend fun fetchGalleryItems(query: String): List<GalleryItem> {
        return if (query.isNotEmpty()) {
            photoRepository.searchPhotos(query)
        } else {
            photoRepository.fetchPhotos()
        }
    }
}