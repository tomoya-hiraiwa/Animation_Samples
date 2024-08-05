package com.example.animationsample

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animationsample.databinding.ActivityButtonRotationAnimationBinding

class ButtonRotationAnimation : AppCompatActivity() {
    private lateinit var b: ActivityButtonRotationAnimationBinding
    private var animationDuration = 100L
    private var arrowRotationValue = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityButtonRotationAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            seekbar.addOnChangeListener { slider, value, fromUser ->
                animationDuration = value.toLong()
            }
            button.setOnClickListener {
                rotation()
            }
            arrowButton.setOnClickListener {
                rotationArrow()
            }
        }
    }
    private fun rotation(){
        b.apply {
        ObjectAnimator.ofFloat(button,"rotation",0f,360f).apply {
            duration = animationDuration

        }.start()
            }
    }
    private fun rotationArrow(){
        b.apply {
            arrow.animate().apply {
                arrowRotationValue = if (arrowRotationValue == 180f) 0f else 180f
             rotation(arrowRotationValue)
                 duration = animationDuration
                start()
            }
        }
    }
}