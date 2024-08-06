package com.example.animationsample.activity_animation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityDialogPopAnimationBinding
import com.example.animationsample.databinding.PopDialogItemBinding
import kotlinx.coroutines.launch

class DialogPopAnimation : AppCompatActivity() {
    private lateinit var b: ActivityDialogPopAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDialogPopAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            showButton.setOnClickListener {
                dialog()
            }
        }
    }
    private fun dialog(){
        Dialog(this).apply {
            val db = PopDialogItemBinding.inflate(layoutInflater)
            setContentView(db.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            db.apply {
                val popAnim = AnimationUtils.loadAnimation(this@DialogPopAnimation,R.anim.dialog_popup_anim)
                val closeAnim = AnimationUtils.loadAnimation(this@DialogPopAnimation,R.anim.dialog_close_anim).also {
                    it.setAnimationListener(object : Animation.AnimationListener{
                        override fun onAnimationStart(animation: Animation?) {

                        }
                        override fun onAnimationEnd(animation: Animation?) {
                            cancel()
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                        }
                    })
                }
                root.startAnimation(popAnim)
                close.setOnClickListener {
                   root.startAnimation(closeAnim)
                }
            }
            setCancelable(false)
        }.show()
    }
}