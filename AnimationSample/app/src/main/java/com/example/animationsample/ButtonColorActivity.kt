package com.example.animationsample

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            seekbar.addOnChangeListener { slider, value, fromUser ->
                animationDuration = value.toLong()
            }
        }
    }

    private fun changeColor() {
        b.apply {
            var fromColor = getColor(latestColor)
            var toColor = 0
            do {
                val color = colorListData.random()
                latestColor = color
                toColor = getColor(color)
            } while (fromColor == toColor)
            b.apply {
                ObjectAnimator.ofObject(
                    button,
                    "backgroundColor",
                    ArgbEvaluator(),
                    fromColor,
                    toColor
                ).apply {
                    duration = animationDuration

                }.start()
            }
        }
    }

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