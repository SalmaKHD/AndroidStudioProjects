package com.example.android.guesstheword.screens.score

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.android.guesstheword.R

class ScoreFragmentDirections private constructor() {
    companion object {
        fun actionRestart(): NavDirections = ActionOnlyNavDirections(R.id.action_restart)
    }
}
