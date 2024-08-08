package com.example.animationsample.tp_anim

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityExpressLoadBinding
//create loading image resource in adobe Xd
class ExpressLoadActivity : AppCompatActivity() {
    private lateinit var b: ActivityExpressLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityExpressLoadBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            ObjectAnimator.ofFloat(image,"rotation",0f,360f).apply {
                interpolator = LinearInterpolator()
                duration = 1000
                repeatMode = ObjectAnimator.RESTART
                repeatCount = ObjectAnimator.INFINITE
            }.start()
        }
    }
}