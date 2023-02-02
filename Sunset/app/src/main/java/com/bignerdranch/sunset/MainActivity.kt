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
        val sunYEnd = binding.sky.height.toFloat()

        // use an ObjectAnimator instance to join the position of the sun progressively
        val heightAnimator = ObjectAnimator
            .ofFloat(binding.sun, "y", sunYStart, sunYEnd)
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
        val animatorSet = AnimatorSet()
        // heightAnimator: subject of the chain of calls
        // .play() -> a Builder
        animatorSet.play(heightAnimator)
            .with(sunsetSkyAnimator)
            .before(nightSkyAnimator)
        // start the animation
        animatorSet.start()

    }
}