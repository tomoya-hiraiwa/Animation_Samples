package com.example.animationsample.object_anim

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityNumberCountAnimationBinding

class NumberCountAnimation : AppCompatActivity() {
    private lateinit var b: ActivityNumberCountAnimationBinding
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityNumberCountAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        setCount()
        b.apply {
           addButton.setOnClickListener {
               count++
               scrollToBottom()
               scrollToCenter()
           }
        }
    }
    private fun setCount(){
        println(count)
        b.apply {
            text1.text = count.toString()
            text2.text = (count+1).toString()
        }
    }

    private fun scrollToBottom(){
        b.apply {
            val dimen = resources.getDimension(R.dimen.count_offset_end)
            ObjectAnimator.ofFloat(text1,"translationY",dimen).apply {
                duration = 300
                addListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator) {

                    }

                    override fun onAnimationEnd(animation: Animator) {
                        text1.translationY = 0f
                        text1.alpha = 1f
                        text2.alpha = 0f
                        setCount()
                    }

                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }

                })
            }.start()
            ObjectAnimator.ofFloat(text1,"alpha",0f).apply {
                duration = 200
            }.start()
        }
    }
    private fun scrollToCenter(){
        b.apply {
            val dimen = resources.getDimension(R.dimen.count_offset)
            ObjectAnimator.ofFloat(text2,"translationY",dimen,0f).apply {
                addUpdateListener {
                    println(animatedValue as Float)
                }
                duration = 300
            }.start()
            ObjectAnimator.ofFloat(text2,"alpha",1f).apply {
                duration = 200
            }.start()
        }
    }
}