package com.example.animationsample.dynamic_anim

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.annotation.RequiresApi
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityProgressCircleIndicatorBinding

class ProgressCircleIndicator : AppCompatActivity() {
    private lateinit var b: ActivityProgressCircleIndicatorBinding
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityProgressCircleIndicatorBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            counterNumber.maxValue = 100
            counterNumber.minValue = 0
            counterNumber.isEnabled = false
            counterNumber.textColor = Color.BLACK
            ValueAnimator.ofFloat(0f,100f).apply {
                duration = 3000
                addUpdateListener {
                    progressCircle.nowValue = animatedValue as Float
                    progressCircle.invalidate()
                    println(animatedValue as Float)
                    counterNumber.value = (animatedValue as Float).toInt()
                }
                interpolator = DecelerateInterpolator()
            }.start()
        }
    }
}