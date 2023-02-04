package com.salmakhd.androidlifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AndroidLifecycleViewModel: ViewModel() {
    // create a variable that will keep track of the current number of the timer
    private val _uiState: MutableStateFlow<Int> = MutableStateFlow(0)

    // create a variable that will supply the UI with the data it needs
    val uiState: StateFlow<Int>
        get() = _uiState.asStateFlow()

    private var timerDone = false

    fun setTimerValue(timerValue: Int) {
        _uiState.value = timerValue
    }

    fun startTimer() {
        viewModelScope.launch {
            while (!timerDone) {
                delay(1000)
                _uiState.value++
            }
        }
    }

    fun stopTimer() {
        timerDone = true
    }
}