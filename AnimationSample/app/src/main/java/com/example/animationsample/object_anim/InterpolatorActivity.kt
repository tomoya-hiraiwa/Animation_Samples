package com.example.animationsample.object_anim

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BaseInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityInterpolatorBinding

class InterpolatorActivity : AppCompatActivity() {
    private lateinit var b: ActivityInterpolatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityInterpolatorBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            adStartButton.setOnClickListener {
                moveAnim(adItem,AccelerateDecelerateInterpolator())
            }
            aStartButton.setOnClickListener {
                moveAnim(aItem,AccelerateInterpolator())
            }
            antiStartButton.setOnClickListener {
                moveAnim(antiItem,AnticipateInterpolator())
            }
            antiOverStartButton.setOnClickListener {
                moveAnim(antiOverItem,AnticipateOvershootInterpolator())
            }
            bounceStartButton.setOnClickListener {
                moveAnim(bounceItem,BounceInterpolator())
            }
            cycleStartButton.setOnClickListener {
                moveAnim(cycleItem,CycleInterpolator(2f))
            }
            decStartButton.setOnClickListener {
                moveAnim(decItem,DecelerateInterpolator())
            }
            lineStartButton.setOnClickListener {
                moveAnim(lineItem,LinearInterpolator())
            }
            overStartButton.setOnClickListener {
                moveAnim(overItem,OvershootInterpolator())
            }
        }
    }
    private fun moveAnim(view: View, myInterpolator: Interpolator){
        ObjectAnimator.ofFloat(view,"translationX",0f,600f).apply {
            interpolator = myInterpolator
            duration = 1500
        }.start()
    }
}