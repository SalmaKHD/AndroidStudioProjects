package com.example.android.guesstheword.screens.score

import android.os.Bundle
import androidx.navigation.NavArgs
import kotlin.Int
import kotlin.jvm.JvmStatic

data class ScoreFragmentArgs(val score: Int = 0) : NavArgs {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putInt("score", this.score)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): ScoreFragmentArgs {
            bundle.setClassLoader(ScoreFragmentArgs::class.java.classLoader)
            val __score : Int
            if (bundle.containsKey("score")) {
                __score = bundle.getInt("score")
            } else {
                __score = 0
            }
            return ScoreFragmentArgs(__score)
        }
    }
}
