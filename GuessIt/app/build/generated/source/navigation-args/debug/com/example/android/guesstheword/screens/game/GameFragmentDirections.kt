package com.example.android.guesstheword.screens.game

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.android.guesstheword.R
import kotlin.Int

class GameFragmentDirections private constructor() {
    private data class ActionGameToScore(val score: Int = 0) : NavDirections {
        override fun getActionId(): Int = R.id.action_game_to_score

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putInt("score", this.score)
            return result
        }
    }

    companion object {
        fun actionGameToScore(score: Int = 0): NavDirections = ActionGameToScore(score)
    }
}
