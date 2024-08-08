package com.example.animationsample.tp_anim

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityExpressBookingIndicatorBinding
import com.google.android.material.divider.MaterialDivider

class ExpressBookingIndicatorActivity : AppCompatActivity() {
    private lateinit var b:ActivityExpressBookingIndicatorBinding
    private val indicatorList = mutableListOf<MaterialDivider>()
    private val ovalList = mutableListOf<ImageView>()
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityExpressBookingIndicatorBinding.inflate(layoutInflater)
        setContentView(b.root)
        addViewList()
        b.apply {
            println(oval1.alpha)
            addButton.setOnClickListener {
                position +=1
                showAnim(ovalList[position-1])
                println(oval1.alpha)
                changeBlue(indicatorList[position-1])
                subButton.isEnabled = true
                if (position == 4){
                    addButton.isEnabled = false
                }
            }
            subButton.setOnClickListener {
                position-=1
                removeAnim(ovalList[position])
                changeGray(indicatorList[position])
                addButton.isEnabled = true
                if(position == 0){
                    subButton.isEnabled = false
                }
            }
        }
    }
    private fun showAnim(view: View){
        ObjectAnimator.ofFloat(view,"alpha",1f).apply {
            duration =200
        }.start()
    }
    private fun removeAnim(view: View){
        ObjectAnimator.ofFloat(view,"alpha",0f).apply {
            duration = 200
        }.start()
    }
    private fun changeBlue(divider: MaterialDivider){
        divider.dividerColor = getColor(R.color.express_blue)
    }
    private fun changeGray(divider: MaterialDivider){
        divider.dividerColor = getColor(R.color.express_gray)
    }


    private fun addViewList(){
        indicatorList.add(b.div1)
        indicatorList.add(b.div2)
        indicatorList.add(b.div3)
        indicatorList.add(b.div4)

        ovalList.add(b.oval1)
        ovalList.add(b.oval2)
        ovalList.add(b.oval3)
        ovalList.add(b.oval4)
    }
}