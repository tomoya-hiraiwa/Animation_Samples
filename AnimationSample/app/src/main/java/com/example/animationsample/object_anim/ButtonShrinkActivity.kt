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
            //set total animation time.
            seekbar.addOnChangeListener { slider, value, fromUser ->
                animationDuration = value.toLong()
            }
        }
    }
    //Shrink function using animate() method
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

    //Shrink function using ObjectAnimator
    private fun objectShrink(){
        b.apply {
            //create objectAnimator that scales down the X and Y value.
            val scaleDownX = ObjectAnimator.ofFloat(objButton, View.SCALE_X,0.9f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            val scaleDownY = ObjectAnimator.ofFloat(objButton, View.SCALE_Y,0.9f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            //create objectAnimator that scales up the X and Y value.
            val scaleUpX = ObjectAnimator.ofFloat(objButton, View.SCALE_X,1.0f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            val scaleUpY = ObjectAnimator.ofFloat(objButton, View.SCALE_Y,1.0f).apply {
                duration = animationDuration/2
                interpolator = AccelerateDecelerateInterpolator()
            }
            //only start scaleDown animation.
            scaleDownX.start()
            scaleDownY.start()
            //set listener in the scaleDown animator.
            scaleDownX.addListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    //start scaleUp animation when scaleDown animation finishes.
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