package com.salmakhd.android.forpractice

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UiState {
    data class Success(val users: List<User>): UiState
    object Loading: UiState
    object Error: UiState
}

class MainScreenViewModel: ViewModel() {
    // create a custom coroutine scope
    private val myCoroutineScope = CoroutineScope(Dispatchers.IO)
    private val myJob = Job()
    // create a repository instance
    private val userRepo = UserRepository()
    // create a state flow for updating list of users in the UI once they are available
    var _UiState = mutableStateOf(UiState.Loading)

    init {
        getUserData()
    }

    fun getUserData() {
//        myCoroutineScope.launch {
//
//        }
        // better alternative: use viewModelScope
        viewModelScope.launch {
            // get users from the repository
            _uiState.value = userRepo.getUsers()

        }
    }

    override fun onCleared() {
        super.onCleared()
        // cancel the coroutine manually
        // alternative: ViewModelScope -> coroutines will be tied to the lifecycle of the ViewModel
        myJob.cancel() // not needed with viewModelScope
    }
}