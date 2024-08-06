package com.example.animationsample.list_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.animationsample.databinding.ActivityDragListAnimationBinding
import com.example.animationsample.databinding.SampleListItemBinding

class DragListAnimation : AppCompatActivity() {
    private lateinit var b: ActivityDragListAnimationBinding
    private lateinit var adapter: DragListAdapter
    private val dataList = mutableListOf<String>("A", "B", "C", "D", "E", "F", "G", "H")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDragListAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            list.layoutManager = GridLayoutManager(this@DragListAnimation, 2)
            adapter = DragListAdapter(dataList)
            list.adapter = adapter
            val helper = ItemTouchHelper(DragHelper())
            helper.attachToRecyclerView(list)
        }
    }
    //Drag function using ItemTouchHelper
    inner class DragHelper() : ItemTouchHelper.SimpleCallback(
        //set Drag flags
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
        //set swipe flags(when not use, set 0 or ItemTouchHelper.ACTION_STATE_IDLE)
        0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            //delete data in drag start position item.
            val data = dataList.removeAt(viewHolder.adapterPosition)
            //add data in drag end position.
            dataList.add(target.adapterPosition, data)
            adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        }

    }
}

class DragListAdapter(private val dataList: List<String>) :
    RecyclerView.Adapter<DragListAdapter.DragListViewHolder>() {
    inner class DragListViewHolder(private val b: SampleListItemBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bindData(data: String) {
            b.apply {
                text.text = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragListViewHolder {
        return DragListViewHolder(
            SampleListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DragListViewHolder, position: Int) {
        holder.bindData(data = dataList[position])
    }
}