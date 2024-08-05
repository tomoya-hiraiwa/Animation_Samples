package com.example.animationsample

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.animationsample.databinding.ActivityBounceAnimationBinding

class BounceAnimation : AppCompatActivity() {
    private lateinit var b: ActivityBounceAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityBounceAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            button.setOnClickListener {
                simpleBounce()
            }
            errorButton.setOnClickListener {
                errorBounce()
            }
            splingItem.setOnClickListener {
                springBounce()
            }
        }
    }
    private fun simpleBounce() {
        b.apply {
            ObjectAnimator.ofFloat(button,"translationY",0f,-20f,0f).apply {
                duration = 500
                interpolator = LinearInterpolator()
            }.start()
            }
    }
    private fun errorBounce(){
        b.apply {
            ObjectAnimator.ofFloat(errorButton,"translationX",0f,20f,-20f,10f,-10f,0f).apply {
                duration = 500
                interpolator = LinearInterpolator()
            }.start()
        }
    }
    private fun springBounce(){
        b.apply {
            SpringAnimation(splingItem,DynamicAnimation.TRANSLATION_Y,300f).apply {
                spring.apply {
                    stiffness = SpringForce.STIFFNESS_VERY_LOW
                    dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                }
                addEndListener { animation, canceled, value, velocity ->
                    splingItem.translationY = 0f
                }
            }.start()
        }
    }
}