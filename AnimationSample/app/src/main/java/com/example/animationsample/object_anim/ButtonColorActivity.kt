package com.example.animationsample.object_anim

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animationsample.R
import com.example.animationsample.colorListData
import com.example.animationsample.databinding.ActivityButtonColorBinding

class ButtonColorActivity : AppCompatActivity() {
    private lateinit var b: ActivityButtonColorBinding
    private var animationDuration = 1000L
    var latestColor = R.color.color1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityButtonColorBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            button.setOnClickListener {
                changeColor()
            }
            withShrinkButton.setOnClickListener {
                withShrink()
            }
            //set total animation time.
            seekbar.addOnChangeListener { slider, value, fromUser ->
                animationDuration = value.toLong()
            }
        }
    }
    //change color function using objectAnimator
    private fun changeColor() {
        b.apply {
            //set start and end color
            var fromColor = getColor(latestColor)
            var toColor = 0
            //get end color until it is different from the start color.
            do {
                val color = colorListData.random()
                latestColor = color
                toColor = getColor(color)
            } while (fromColor == toColor)
            b.apply {
                ObjectAnimator.ofObject(
                    button,
                    "backgroundColor",
                    ArgbEvaluator(),  //this evaluator is used to animate the color change.
                    fromColor,
                    toColor
                ).apply {
                    duration = animationDuration

                }.start()
            }
        }
    }
    //color change animation with shrink
    private fun withShrink() {
        var fromColor = getColor(latestColor)
        var toColor = 0
        do {
            val color = colorListData.random()
            latestColor = color
            toColor = getColor(color)
        } while (fromColor == toColor)
        b.apply {
            ObjectAnimator.ofObject(withShrinkButton, "backgroundColor", ArgbEvaluator(), fromColor, toColor)
                .apply {
                    duration = animationDuration
                }.start()
            withShrinkButton.animate().apply {
                scaleX(0.5f)
                scaleY(0.5f)
                duration = animationDuration/2
                withEndAction {
                    scaleX(1.0f)
                    scaleY(1.0f)
                    duration = animationDuration/2
                }
                start()
            }
        }
    }
}