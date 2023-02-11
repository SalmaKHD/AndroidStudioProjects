package com.salmakhd.androidlifecycle

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

private const val TIMER_VALUE = "Timer Value"
class AndroidLifecycleViewModel(
    private val state: SavedStateHandle
): ViewModel() {
    init{
        //state[TIMER_VALUE] = 81
        Log.i("AndroidLifecycleViewModel", (state[TIMER_VALUE] ?: 0).toString())
    }

    // check if the timer should still go on
    private var timerDone = false

    // create a variable that will keep track of the current number of the timer
    private val _uiState: MutableStateFlow<Int> = MutableStateFlow(state[TIMER_VALUE] ?: 90)

    // create a variable that will supply the UI with the data it needs
    val uiState: StateFlow<Int>
        get() = _uiState.asStateFlow()

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

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
        state[TIMER_VALUE] = uiState.value
    }
}