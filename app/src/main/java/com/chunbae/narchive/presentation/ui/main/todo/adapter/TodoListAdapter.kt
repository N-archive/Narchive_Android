package com.chunbae.narchive.presentation.ui.main.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.databinding.ItemTodoRvTodoItemsFormBinding
import com.chunbae.narchive.presentation.util.ReturnColorCode
import kotlin.math.abs

class TodoListAdapter (private val onCheckClicked : (Int) -> Unit, private val onLongClicked : (Int) -> Unit): RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    var todoList = mutableListOf<TodoData.TodoList>()
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

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.bind(todoList[position])
    }
}