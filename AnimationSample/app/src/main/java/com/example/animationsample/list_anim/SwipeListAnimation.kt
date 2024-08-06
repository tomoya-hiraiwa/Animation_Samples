package com.example.animationsample.list_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivitySwipeListAnimationBinding
import com.example.animationsample.databinding.SwipeSampleListItemBinding

class SwipeListAnimation : AppCompatActivity() {
    private lateinit var b: ActivitySwipeListAnimationBinding
    private lateinit var adapter: SwipeListAdapter
    private val sampleData = mutableListOf<String>(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySwipeListAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            list.layoutManager = LinearLayoutManager(this@SwipeListAnimation)
            adapter = SwipeListAdapter(sampleData)
            val helper = ItemTouchHelper(ListHelper())
            list.adapter =adapter
            helper.attachToRecyclerView(list)
        }
    }
    //set swipe function using ItemTouchHelper
    inner class ListHelper: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            sampleData.removeAt(viewHolder.adapterPosition)
            adapter.notifyItemRemoved(viewHolder.adapterPosition)
        }

    }
}

 private class SwipeListAdapter(private val dataList: MutableList<String>): RecyclerView.Adapter<SwipeListAdapter.SwipeListViewHolder>(){
     inner class SwipeListViewHolder(private val b: SwipeSampleListItemBinding): RecyclerView.ViewHolder(b.root){
         fun bindData(data: String){
             b.apply {
                 name.text = data

             }
         }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeListViewHolder {
         return SwipeListViewHolder(SwipeSampleListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
     }

     override fun getItemCount(): Int {
         return dataList.size
     }

     override fun onBindViewHolder(holder: SwipeListViewHolder, position: Int) {
         holder.bindData(data = dataList[position])
     }
 }
