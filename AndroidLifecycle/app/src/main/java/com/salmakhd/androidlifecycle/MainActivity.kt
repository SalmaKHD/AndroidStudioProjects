package com.salmakhd.androidlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.salmakhd.androidlifecycle.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TIMER_VALUE = "Timer value"
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    // create a variable to fetch UI state data
    private lateinit var viewModel: AndroidLifecycleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(AndroidLifecycleViewModel::class.java)

        // start the timer or continue from where the user left off before the activity was destructed
        lifecycleScope.launch {
            viewModel.uiState.collect {
                binding.timerTextView.text = viewModel.uiState.value.toString()
            }
        }

        // check if the last state of the UI needs to be restored
        if (savedInstanceState != null) {
            // fetch the last value of the timer before the app process was killed
            var timerValue = savedInstanceState.getInt(TIMER_VALUE)
            Timber.i("savedInstance check passed.")
            // update the value of the timer in the viewModel
            viewModel.setTimerValue(timerValue)
        }

        binding.startTimerButton.setOnClickListener {
            // start the timer
            viewModel.startTimer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stopTimer()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        // call to the super class constructor important for saving vital UI data
        super.onSaveInstanceState(outState)
        outState.putInt(TIMER_VALUE, viewModel.uiState.value)
        Timber.i("onSaveInstance called: ${viewModel.uiState.value}")
    }
}