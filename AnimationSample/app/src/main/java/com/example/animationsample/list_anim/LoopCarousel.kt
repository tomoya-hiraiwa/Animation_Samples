package com.example.animationsample.list_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityLoopCarouselBinding
import com.example.animationsample.databinding.CarouselItemBinding
import com.example.animationsample.databinding.LoopPagerItemBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoopCarousel : AppCompatActivity() {
    private lateinit var b: ActivityLoopCarouselBinding
    private val sampleData = listOf<Int>(
        R.drawable.carousel1,
        R.drawable.carousel2,
        R.drawable.carousel3,
        R.drawable.carousel4,
        R.drawable.carousel5
    )
    //scope the real position.
    private var realPosition = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoopCarouselBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            pager.adapter = LoopCarouselAdapter(sampleData)
            pager.setCurrentItem(realPosition,false)
            //Disable user touch.
            pager.isUserInputEnabled = false
            pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    realPosition = position
                }

                override fun onPageScrollStateChanged(state: Int) {
                    //when pager scroll is end
                    if(state == ViewPager2.SCROLL_STATE_IDLE){
                        //position is front dummy position
                        if (realPosition == 0){
                            realPosition = pager.adapter!!.itemCount -2
                            pager.setCurrentItem(realPosition,false)
                        }
                        //position is end dummy position
                        else if ((realPosition == pager.adapter!!.itemCount -1)){
                            realPosition = 1
                            pager.setCurrentItem(realPosition,false)
                        }
                    }
                }
            })
            //set count of visible offset view.
            pager.offscreenPageLimit = 2
            //set page shape transformer
            pager.setPageTransformer { page, position ->
                val offset = resources.getDimensionPixelOffset(R.dimen.card_offset)
                val padding = resources.getDimensionPixelOffset(R.dimen.card_padding)
                val translation = -position *(2 * (offset +padding))
                //set translationX to show offset view.
                page.translationX = translation
            }
            //automatic scroll page.
            lifecycleScope.launch {
                while (true){
                    delay(2000)
                    realPosition +=1
                    pager.currentItem = realPosition
                }
            }
        }
    }
}
class LoopCarouselAdapter(private val dataList: List<Int>) :
    RecyclerView.Adapter<LoopCarouselAdapter.LoopCarouselViewHolder>() {
    var addCount = dataList.size +2
    inner class LoopCarouselViewHolder(private val b: LoopPagerItemBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bindData(data: Int) {
            b.apply {
                image.load(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoopCarouselViewHolder {
        return LoopCarouselViewHolder(
            LoopPagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return addCount
    }

    override fun onBindViewHolder(holder: LoopCarouselViewHolder, position: Int) {
        val realPosition = when(position){
             0 -> dataList.size -1
             dataList.size +1  -> 0
            else -> position -1
        }
        holder.bindData(dataList[realPosition])
    }
}