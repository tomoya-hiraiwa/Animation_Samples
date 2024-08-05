package com.example.animationsample.tp_anim

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import androidx.annotation.RequiresApi
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivitySampleAmsplashBinding
import kotlin.math.log

class SampleAMSplashActivity : AppCompatActivity() {
    private lateinit var b: ActivitySampleAmsplashBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySampleAmsplashBinding.inflate(layoutInflater)
        setContentView(b.root)
        window.insetsController?.apply {
            hide(WindowInsets.Type.systemBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        b.apply {
            logo.rotation = -180f
            logo.animate().apply {
                rotation(0f)
                duration = 1500
                interpolator = AccelerateDecelerateInterpolator()
            }
            shader.startAnimation(AlphaAnimation(1f,0f).apply {
                duration = 1000
                fillAfter = true
            })
            logoScroll()
            imageScroll()
        }
    }
    private fun logoScroll(){
        b.apply {
            val startOffset = resources.getDimensionPixelOffset(R.dimen.logo_translation).toFloat()
            ObjectAnimator.ofFloat(logo,"translationX",startOffset,0f).apply {
                duration = 1500
                interpolator = DecelerateInterpolator()
            }.start()
        }
    }
    private fun imageScroll(){
        b.apply {
            ObjectAnimator.ofFloat(backImage,"translationX",-800f).apply {
                duration = 1200
                interpolator = DecelerateInterpolator()
            }.start()
        }
    }

}