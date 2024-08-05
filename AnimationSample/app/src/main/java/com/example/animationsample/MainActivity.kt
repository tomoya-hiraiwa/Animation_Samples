package com.example.animationsample

import android.app.LauncherActivity.ListItem
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.animationsample.databinding.ActivityMainBinding
import com.example.animationsample.databinding.HomeCategoryItemBinding
import com.example.animationsample.databinding.HomeListItemBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            list.layoutManager = LinearLayoutManager(this@MainActivity)
            list.adapter = HomeListAdapter(animationListData){
                lifecycleScope.launch {
                    delay(200)
                startActivity(Intent(this@MainActivity,it.page::class.java))
                }
            }
        }
    }
}

class HomeListAdapter(private val dataList: MutableList<AnimationData>,val onClick: (AnimationData) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    inner class TypeItemViewHolder(private val b: HomeCategoryItemBinding): RecyclerView.ViewHolder(b.root){
        fun bindData(data: AnimationData){
            b.apply {
                root.text = data.title
            }
        }
    }

    inner class ListItemViewHolder(private val b: HomeListItemBinding): RecyclerView.ViewHolder(b.root){
        fun bindData(data: AnimationData){
            b.apply {
                title.text = data.title
                root.setOnClickListener {
                    onClick(data)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(dataList[position].pageType){
            -1 -> return 0
            else -> return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> TypeItemViewHolder(HomeCategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> ListItemViewHolder(HomeListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TypeItemViewHolder) holder.bindData(data = dataList[position])
        if (holder is ListItemViewHolder) holder.bindData(data = dataList[position])
    }

}