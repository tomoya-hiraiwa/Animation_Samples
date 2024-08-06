package com.example.animationsample.icon_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.CycleInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.core.view.isVisible
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivitySimpleIconAnimationBinding

class SimpleIconAnimation : AppCompatActivity() {
    private lateinit var b: ActivitySimpleIconAnimationBinding
    //play button flag.
    private var isPlay = false
    //thumb up flag
    private var isGood = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySimpleIconAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        downLoadAnimation()
        b.apply {
                playFrame.setOnClickListener {
                    playIconAnimation()
                }
            thumbFrame.setOnClickListener {
                thumbChange()
            }
            like.setOnClickListener {
                likeAnim()
            }
        }
    }
    //change play icon animation.
    private fun playIconAnimation(){
        b.apply {
            if (!isPlay) {
                toSmallAnimation(play)
                toBigAnimation(pause)
            }
            else {
                toSmallAnimation(pause)
                toBigAnimation(play)
            }
            isPlay = !isPlay
        }
    }
    //animation where the view shrinks.
    private fun toSmallAnimation(view: View){
        view.startAnimation(ScaleAnimation(1f,0f,1f,0f).apply {
            duration = 300
            interpolator = AccelerateInterpolator()
        })
        view.isVisible = false
    }
    //animation where the view enlarges.
    private fun toBigAnimation(view: View){
        view.isVisible = true
        view.startAnimation(ScaleAnimation(0f,1f,0f,1f).apply {
            interpolator = AccelerateInterpolator()
            duration = 300
            startOffset = 100
        })
    }
    //change thumb up icon with animation
    private fun thumbChange(){
        b.apply {
            val small = AnimationUtils.loadAnimation(this@SimpleIconAnimation, R.anim.thumb_small)
            val big = AnimationUtils.loadAnimation(this@SimpleIconAnimation, R.anim.thumb_big)
            if (!isGood){
                thumbOff.startAnimation(small)
                thumbOff.isVisible = false
                thumbOn.isVisible  =true
                thumbOn.startAnimation(big.apply {
                    startOffset = 100
                })
            }
            else {
                thumbOn.startAnimation(small)
                thumbOn.isVisible = false
                thumbOff.isVisible  =true
                thumbOff.startAnimation(big.apply {
                    startOffset = 100
                })
            }
            isGood = !isGood
        }
    }
    //floating shadow animation when click the icon.
    private fun likeAnim(){
        b.apply {
            val animation = AnimationUtils.loadAnimation(this@SimpleIconAnimation, R.anim.like_back)
            likeShadow.startAnimation(animation)
        }
    }
    //infinite animation like download icon.
    private fun downLoadAnimation(){
        b.apply {
            val animation = TranslateAnimation(1f,1f,1f,40f).apply {
                duration = 1000
                interpolator = CycleInterpolator(1f)
                repeatCount = Animation.INFINITE
            }
            download.startAnimation(animation)
        }
    }
}