package com.bignerdranch.android.photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.photogallery.api.GalleryItem
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"
class PhotoGalleryViewModel: ViewModel() {
    private val photoRepository:PhotoRepository = PhotoRepository()

    // use PhotoGalleryUiState to combine StateFlow objects
    private val _uiState: MutableStateFlow<PhotoGalleryUiState> =
        MutableStateFlow(PhotoGalleryUiState())

    val uiState: StateFlow<PhotoGalleryUiState>
        get() = _uiState.asStateFlow()

    // get the singleton to write to shared preferences
    private val preferencesRepository = PreferencesRepository.get()

    init{
        // call the fetchContents() function to issue a web request in a coroutine scope
        viewModelScope.launch {
            // restore the latest query made in the app
            /*
            Note: collect{} vs collectLatest{} -> The last running block will be canceled
            immediately when the value in the stream changes
             */
            preferencesRepository.storedQuery.collectLatest { storedQuery->
                // catch possible errors Flickr may throw
                try {
                    val items = fetchGalleryItems(storedQuery)
                    _uiState.update { oldState ->
                        oldState.copy(
                            // update fragment UI state
                            images = items,
                            query = storedQuery
                        )
                    }
                } catch (ex:Exception) {
                    Log.e(TAG, "failed to fetch gallery items", ex)
                }
            }
        }

        viewModelScope.launch {
            preferencesRepository.isPolling.collect { isPolling ->
                // change the UI state as soon as polling option is toggled
                _uiState.update { it.copy(isPolling = isPolling) }
            }
        }
    }

    // update images stream based on the passed query
    fun setQuery(query: String) {
        // change the query stored in shared preferences
        viewModelScope.launch { preferencesRepository.setStoredQuery(query) }
    }

    // toggle polling
    fun toggleIsPolling() {
        viewModelScope.launch {
            preferencesRepository.setPolling(!uiState.value.isPolling)
        }
    }

    // fetch images based on a query
    private suspend fun fetchGalleryItems(query: String): List<GalleryItem> {
        return if (query.isNotEmpty()) {
            photoRepository.searchPhotos(query)
        } else {
            photoRepository.fetchPhotos()
        }
    }

    data class PhotoGalleryUiState (
        val images: List<GalleryItem> = listOf(),
        val query: String = "",
        val isPolling: Boolean = false,
        )
}