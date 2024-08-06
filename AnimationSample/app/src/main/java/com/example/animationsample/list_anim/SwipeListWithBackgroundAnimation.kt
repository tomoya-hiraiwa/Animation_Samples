package com.example.animationsample.list_anim

import android.content.ClipData.Item
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowInsetsAnimation.Bounds
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivitySwipeListWithBacgroundAnimationBinding
import com.example.animationsample.databinding.SwipeSampleListItemBinding

class SwipeListWithBackgroundAnimation : AppCompatActivity() {
    private lateinit var b: ActivitySwipeListWithBacgroundAnimationBinding
    private lateinit var adapter: SwipeWithBackAdapter
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
        b = ActivitySwipeListWithBacgroundAnimationBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.apply {
            list.layoutManager = LinearLayoutManager(this@SwipeListWithBackgroundAnimation)
            adapter = SwipeWithBackAdapter(sampleData)
            list.adapter = adapter
            val helper = ItemTouchHelper(SwipeHelper())
            helper.attachToRecyclerView(list)
        }
    }

    inner class SwipeHelper : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }
        //set background view in onChildDraw function
        @RequiresApi(Build.VERSION_CODES.R)
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            //get target itemView from recyclerView.ViewHolder
            val itemView = viewHolder.itemView
            //set background color
            val backGround = ColorDrawable(Color.RED)
            //set icon
            val icon = getDrawable(R.drawable.baseline_delete_24)!!
            //set icon padding
            val iconPadding = (itemView.height - icon.intrinsicHeight) / 2
            //set icon draw place
            icon.setBounds(
                itemView.left + iconPadding,
                itemView.top + iconPadding,
                (itemView.left + iconPadding) + icon.intrinsicWidth,
                itemView.bottom - iconPadding
            )
            //set background draw place
            backGround.setBounds(
                itemView.left,
                itemView.top,
                itemView.left + dX.toInt(),
                itemView.bottom
            )
            backGround.draw(c)
            icon.draw(c)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            sampleData.removeAt(viewHolder.adapterPosition)
            adapter.notifyItemRemoved(viewHolder.adapterPosition)
        }

    }

}

private class SwipeWithBackAdapter(private val dataList: MutableList<String>) :
    RecyclerView.Adapter<SwipeWithBackAdapter.SwipeListViewHolder>() {
    inner class SwipeListViewHolder(private val b: SwipeSampleListItemBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bindData(data: String) {
            b.apply {
                name.text = data

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeListViewHolder {
        return SwipeListViewHolder(
            SwipeSampleListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: SwipeListViewHolder, position: Int) {
        holder.bindData(data = dataList[position])
    }
}