package com.salmakhd.android.amphibiansapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.salmakhd.android.amphibiansapp.AmphibiansApplication
import com.salmakhd.android.amphibiansapp.data.AmphibianRepository
import com.salmakhd.android.amphibiansapp.network.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException

// define states for the screen
sealed interface AmphibianUiState {
    data class Success(val amphibians: List<Amphibian>): AmphibianUiState
    object Error: AmphibianUiState
    object Loading: AmphibianUiState
}
class AmphibianViewModel(
    private val amphibianRepository: AmphibianRepository
) : ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
    private set

    init{
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                AmphibianUiState.Success(amphibianRepository.getAmphibians())
            } catch (e: IOException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibianRepository = application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}