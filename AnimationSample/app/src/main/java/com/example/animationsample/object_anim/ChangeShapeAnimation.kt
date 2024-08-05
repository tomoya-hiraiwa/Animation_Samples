package com.example.animationsample.object_anim

import android.animation.Animator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityChangeShapeAnimationBinding

class ChangeShapeAnimation : AppCompatActivity() {
    private lateinit var b: ActivityChangeShapeAnimationBinding
    private var animationDuration = 100L
    private var isBig = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityChangeShapeAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            seekbar.addOnChangeListener { slider, value, fromUser ->
                animationDuration = value.toLong()
            }
            button.setOnClickListener {
                changeCorner()
            }
            sizeButton.setOnClickListener {
                changeSize()
            }
        }
    }
    private fun changeCorner(){
        val cornerRange = 1..100
        val cornerValue = cornerRange.random()
        b.apply {
            ValueAnimator.ofInt(button.cornerRadius,cornerValue).apply {
                addUpdateListener {
                    button.cornerRadius = animatedValue as Int
                }
                duration  = animationDuration
            }.start()
        }
    }
    private fun changeSize() {
        b.apply {
            val smallSize = resources.getDimensionPixelSize(R.dimen.button_size)
            val bigSize = 600
            ValueAnimator.ofInt(
                sizeButton.width,
                if (!isBig) bigSize else smallSize
            ).apply {
                addUpdateListener {
                    val value = animatedValue as Int
                    val params = sizeButton.layoutParams
                    params.width = value
                    params.height = value
                    sizeButton.layoutParams = params
                }
                addListener(object: Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator) {

                    }

                    override fun onAnimationEnd(animation: Animator) {
                        if (isBig){
                            sizeButton.text = "Large"
                        }
                        else sizeButton.text = "Small"
                    }

                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }

                })
                duration  = animationDuration
            }.start()
            isBig  = !isBig
        }
    }
}