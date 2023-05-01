package com.chunbae.narchive.presentation.ui.main.calendar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.databinding.ItemCalendarTodoFormBinding
import com.chunbae.narchive.presentation.util.ReturnColorCode
import com.chunbae.narchive.presentation.util.TodoColor
import com.chunbae.narchive.presentation.util.setBackground

class CalendarTodoAdapter : RecyclerView.Adapter<CalendarTodoAdapter.CalendarTodoViewHolder>() {
    var todoItems = mutableListOf<TodoData?>()

    inner class CalendarTodoViewHolder(private val binding : ItemCalendarTodoFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : TodoData) {
            binding.todoColor = item.groupColor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarTodoViewHolder {
        return CalendarTodoViewHolder(ItemCalendarTodoFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = todoItems.size

    override fun onBindViewHolder(holder: CalendarTodoViewHolder, position: Int) {
        todoItems[position]?.let { holder.bind(it) }
    }
}