package com.example.animationsample.list_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityCaroucelBinding
import com.example.animationsample.databinding.CarouselItemBinding

class Carousel : AppCompatActivity() {
    private lateinit var b: ActivityCaroucelBinding
    private val sampleData = listOf<Int>(
        R.drawable.carousel1,
        R.drawable.carousel2,
        R.drawable.carousel3,
        R.drawable.carousel4,
        R.drawable.carousel5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCaroucelBinding.inflate(layoutInflater)
        setContentView(b.root)
        createIndicator()
        b.apply {
            //Can't be click each indicator item.
            for (i in 0 until tab.tabCount) {
                val view = tab.getTabAt(i)?.view
                view?.isClickable = false
            }
            pager.adapter = CarouselAdapter(sampleData)
            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    tab.getTabAt(position)!!.select()
                }
            })
        }
    }
    //create indicator item corresponding the item count.
    private fun createIndicator() {
        b.apply {
            for (i in sampleData.indices) {
                val item = tab.newTab().apply {
                    setIcon(R.drawable.baseline_circle_24)
                }
                tab.addTab(item)
            }
        }
    }
}

class CarouselAdapter(private val dataList: List<Int>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    inner class CarouselViewHolder(private val b: CarouselItemBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bindData(data: Int) {
            b.apply {
                image.load(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            CarouselItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bindData(data = dataList[position])
    }
}