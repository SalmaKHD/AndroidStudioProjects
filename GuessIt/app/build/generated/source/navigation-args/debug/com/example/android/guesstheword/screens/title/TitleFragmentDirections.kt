package com.example.android.guesstheword.screens.title

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.android.guesstheword.R

class TitleFragmentDirections private constructor() {
    companion object {
        fun actionTitleToGame(): NavDirections = ActionOnlyNavDirections(R.id.action_title_to_game)
    }
}
