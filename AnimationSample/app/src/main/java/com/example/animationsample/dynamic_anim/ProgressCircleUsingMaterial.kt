package com.example.animationsample.dynamic_anim

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import coil.memory.MemoryCache
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityProgressCircleUsingMaterialBinding

class ProgressCircleUsingMaterial : AppCompatActivity() {
    private lateinit var b: ActivityProgressCircleUsingMaterialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProgressCircleUsingMaterialBinding.inflate(layoutInflater)
        setContentView(b.root)
        simpleProgressAnim()
        countDownAnim()
        arcProgressAnim()
        b.apply {

        }
    }
    private fun simpleProgressAnim(){
        b.apply {
            ValueAnimator.ofInt(0,100).apply {
                addUpdateListener {
                    progress1.progress = animatedValue as Int
                }
                duration = 3000
                interpolator = AccelerateDecelerateInterpolator()
            }.start()
        }
    }

    private fun countDownAnim(){
        b.apply {
            ValueAnimator.ofInt(100,0).apply {
                addUpdateListener {
                    progress2.progress = animatedValue as Int
                }
                duration = 3000
                interpolator = AccelerateDecelerateInterpolator()
            }.start()
        }
    }
    private fun arcProgressAnim(){
        b.apply {
            ValueAnimator.ofInt(0,50).apply {
                addUpdateListener {
                    progress3.progress = animatedValue as Int
                }
                duration  = 3000
                interpolator  = AccelerateDecelerateInterpolator()
            }.start()
        }
    }
}