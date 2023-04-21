/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

// define different UI states for the screen depending on the
// current status of the network request
sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel(
    private val marsPhotosRepository: MarsPhotosRepository
) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        // disallow changing the value from outside
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            // catch and handle common network exceptions
            marsUiState = try {
                // if not errors, set the state of the UI to success and supply
                // the retrieved photos result
                MarsUiState.Success( marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                // if an error occurs, set the UI state to Error -> UI will comply
                MarsUiState.Error
            }
        }
    }

    // create a Factory class for the ViewModel to pass data to it.
    // this will be used to get the ViewModel for the corresponding screen from
    // this point on
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // get the object that extends Application using this(APPLICATION_KEY)
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                // get the repository stored in the app container
                val marsPhotosRepository = application.container.marsPhotosRepository
                // return a ViewModel with the correct parameters it needs
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}

