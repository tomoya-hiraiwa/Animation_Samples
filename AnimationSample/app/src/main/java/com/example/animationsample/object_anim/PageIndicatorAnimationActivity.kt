package com.example.animationsample.object_anim

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityPageIndicatorAnimationBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_ANIMATION_MODE_LINEAR
import com.google.android.material.tabs.TabLayout.TabIndicatorAnimationMode

class PageIndicatorAnimationActivity : AppCompatActivity() {
    private lateinit var b: ActivityPageIndicatorAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPageIndicatorAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            tabLinear.getTabAt(0)?.icon?.setTint(Color.TRANSPARENT)
            tabElastic.getTabAt(0)?.icon?.setTint(Color.TRANSPARENT)
            tabElastic.tabIndicatorAnimationMode = INDICATOR_ANIMATION_MODE_LINEAR
            tabLinear.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon?.setTint(Color.TRANSPARENT)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon?.setTint(getColor(R.color.express_gray))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
            tabElastic.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon?.setTint(Color.TRANSPARENT)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon?.setTint(getColor(R.color.express_gray))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }
    }
}