package com.chunbae.narchive.presentation.ui.todogroup.adapter.util

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.R
import com.chunbae.narchive.presentation.ui.todogroup.adapter.TodoGroupAdapter

class ItemTouchHelperCallback(val listener: ItemTouchHelperListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.LEFT
        return makeMovementFlags(0, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder.absoluteAdapterPosition)
//        val mViewHolder = viewHolder as TodoGroupAdapter.TodoGroupViewHolder
//        if(direction == ItemTouchHelper.LEFT) {
//            Log.d("----", "onSwiped: SWIPE")
//        }
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }
}