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

class CalendarTodoAdapter(val onClick : () -> Unit) : RecyclerView.Adapter<CalendarTodoAdapter.CalendarTodoViewHolder>() {
    var todoItems = mutableListOf<TodoData.TodoList?>()

    inner class CalendarTodoViewHolder(private val binding : ItemCalendarTodoFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : TodoData.TodoList) {
            binding.todoColor = item.groupColor
            binding.root.setOnClickListener { onClick.invoke() }
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