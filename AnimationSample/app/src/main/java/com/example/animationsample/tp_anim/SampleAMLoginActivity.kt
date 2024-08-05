package com.example.animationsample.tp_anim

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import androidx.annotation.RequiresApi
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivitySampleAmloginBinding

class SampleAMLoginActivity : AppCompatActivity() {
    private lateinit var b: ActivitySampleAmloginBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySampleAmloginBinding.inflate(layoutInflater)
        setContentView(b.root)
        window.insetsController?.apply {
            hide(WindowInsets.Type.systemBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        b.apply {
            logo.startAnimation(TranslateAnimation(1f,1f,1f,-20f).apply {
                duration = 200
                interpolator = LinearInterpolator()
                fillAfter = true
            })
            loginFrame.startAnimation(AlphaAnimation(0f,1f).apply {
                duration = 300
                startOffset =500
            })
            buttonLocationAnim()
        }
    }
    private fun buttonLocationAnim(){
        b.apply {
            ObjectAnimator.ofFloat(loginButton,"translationY",-100f).apply {
                duration = 300
                interpolator = AccelerateDecelerateInterpolator()
                startDelay = 500
            }.start()
            ObjectAnimator.ofFloat(registerButton,"translationY",100f).apply {
                duration = 300
                interpolator = AccelerateDecelerateInterpolator()
                startDelay = 500
            }.start()
        }
    }
}