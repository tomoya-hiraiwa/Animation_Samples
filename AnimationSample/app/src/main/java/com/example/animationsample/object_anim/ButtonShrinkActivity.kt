package com.example.animationsample.object_anim

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.animationsample.databinding.ActivityButtonShrinkBinding

class ButtonShrinkActivity : AppCompatActivity() {
    private lateinit var b: ActivityButtonShrinkBinding
    private var animationDuration = 100L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityButtonShrinkBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {

            button.setOnClickListener {
                buttonShrink()
            }
            objButton.setOnClickListener {
                objectShrink()
            }
            seekbar.addOnChangeListener { slider, value, fromUser ->
                animationDuration = value.toLong()
            }
        }
    }
    private fun buttonShrink(){
        b.button.animate().apply {
            scaleX(0.9f)
            scaleY(0.9f)
            duration = animationDuration/2
            withEndAction {
                scaleX(1.0f)
                scaleY(1.0f)
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
                start()
            }
            start()
        }
    }


    private fun objectShrink(){
        b.apply {
            val scaleDownX = ObjectAnimator.ofFloat(objButton, View.SCALE_X,0.9f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            val scaleDownY = ObjectAnimator.ofFloat(objButton, View.SCALE_Y,0.9f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            val scaleUpX = ObjectAnimator.ofFloat(objButton, View.SCALE_X,1.0f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            val scaleUpY = ObjectAnimator.ofFloat(objButton, View.SCALE_Y,1.0f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            scaleDownX.start()
            scaleDownY.start()
            scaleDownX.addListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    scaleUpX.start()
                    scaleUpY.start()
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }

            })
        }
    }
}