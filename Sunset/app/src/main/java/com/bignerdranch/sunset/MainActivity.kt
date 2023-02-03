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

    // define the color of the backgrounds
    private val blueSkyColor: Int by lazy {
        ContextCompat.getColor(this, R.color.blue_sky)
    }
    private val sunsetSkyColor: Int by lazy {
        ContextCompat.getColor(this, R.color.sunset_sky)
    }
    private val nightSkyColor: Int by lazy {
        ContextCompat.getColor(this, R.color.night_sky)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate the layout and add to activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // start the animation when the user presses anywhere on the screen
        binding.scene.setOnClickListener {
            startAnimation()
        }
    }

    // write the function that will start and end the animation
    private fun startAnimation() {
        val sunYStart = binding.sun.top.toFloat()
        val sunHeight = binding.sky.height.toFloat()

        // use an ObjectAnimator instance to join the position of the sun progressively
        val heightAnimator = ObjectAnimator
            .ofFloat(binding.sun, "y", sunYStart, sunHeight)
            .setDuration(3000)

        // use an AnimationInterpolator to change how the UI element's properties change over time
        heightAnimator.interpolator = AccelerateInterpolator()

        // animate the background sky from blue sky to sunset
        val sunsetSkyAnimator = ObjectAnimator
            .ofInt(binding.sky, "backgroundColor", blueSkyColor, sunsetSkyColor)
            .setDuration(3000)

        // defines how the colors should change from the initial
        // one to the final one
        sunsetSkyAnimator.setEvaluator(ArgbEvaluator())

        // animate the background sky from sunset to night
        val nightSkyAnimator = ObjectAnimator
            .ofInt(binding.sky, "backgroundColor", sunsetSkyColor, nightSkyColor)
            .setDuration(1500)
        nightSkyAnimator.setEvaluator(ArgbEvaluator())

        // define an AnimatorSet for running animations in relation
        // to one another
        val sunsetAnimatorSet = AnimatorSet()
        // heightAnimator: subject of the chain of calls
        // .play() -> a Builder
        sunsetAnimatorSet.play(heightAnimator)
            .with(sunsetSkyAnimator)
            .before(nightSkyAnimator)

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