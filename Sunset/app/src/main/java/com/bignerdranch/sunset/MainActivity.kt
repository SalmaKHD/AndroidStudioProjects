package com.bignerdranch.sunset

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.core.content.ContextCompat
import com.bignerdranch.sunset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    // note: part of challenge
    // define a variable that will decide which animation must be played now
    var sunsetAnimatorTurn = true

    /*
	Part of Code deleted for copyright reasons.
	*/

        // note: part of challenge
        // add an AnimatorSet that plays the animation in reverse order
       val nightSkyToSunriseAnimator = ObjectAnimator
           .ofInt(binding.sky, "backgroundColor", nightSkyColor, sunsetSkyColor)
           .setDuration(3000)
        nightSkyToSunriseAnimator.setEvaluator(ArgbEvaluator())

        val sunriseToBlueSkyAnimator =  ObjectAnimator
            .ofInt(binding.sky, "backgroundColor", sunsetSkyColor, blueSkyColor)
            .setDuration(1500)
        sunriseToBlueSkyAnimator.setEvaluator(ArgbEvaluator())

        // Note: ObjectAnimator will not change the original property values of the UI component
        val sunsetToSunriseSunAnimator = ObjectAnimator
            .ofFloat(binding.sun, "y", sunHeight+sunYStart, binding.sky.height/2.5f)
            .setDuration(3000)
        sunsetToSunriseSunAnimator.interpolator = AccelerateInterpolator()

        val sunriseAnimationSet = AnimatorSet()
        sunriseAnimationSet.play(nightSkyToSunriseAnimator)
            .with(sunsetToSunriseSunAnimator)
            .before(sunriseToBlueSkyAnimator)

        // check which animation must be played now
        if(sunsetAnimatorTurn)
            sunsetAnimatorSet.start()
        else
            sunriseAnimationSet.start()

        // revert the sunsetAnimatorTurn value to play the other animation
        sunsetAnimatorTurn = !sunsetAnimatorTurn
    }
}