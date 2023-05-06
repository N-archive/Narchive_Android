package com.chunbae.narchive.presentation.ui.main.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.databinding.ItemTodoRvTodoItemsFormBinding
import com.chunbae.narchive.presentation.util.ReturnColorCode
import kotlin.math.abs

class TodoListAdapter (private val onCheckClicked : (Int) -> Unit, private val onLongClicked : (Int) -> Unit): ListAdapter<TodoData.TodoList, TodoListAdapter.TodoListViewHolder>(
    diffCallback) {
    inner class TodoListViewHolder(private val binding : ItemTodoRvTodoItemsFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : TodoData.TodoList) {
            binding.todoData = item
            //:TODO 추후 todo index로 변경해야함
            binding.itemTodoRvTodoItemsCheckIsDone.setOnClickListener { onCheckClicked.invoke(absoluteAdapterPosition) }
            binding.root.setOnLongClickListener {
                onLongClicked.invoke(absoluteAdapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        return TodoListViewHolder(ItemTodoRvTodoItemsFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TodoData.TodoList>() {

            override fun areItemsTheSame(oldItem: TodoData.TodoList, newItem: TodoData.TodoList): Boolean {
                return oldItem.todoIdx == newItem.todoIdx
            }

            override fun areContentsTheSame(oldItem: TodoData.TodoList, newItem: TodoData.TodoList): Boolean {
                return oldItem == newItem
            }
        }
    }
}