package com.chunbae.narchive.presentation.ui.main.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.databinding.ItemDialogWriteTodoGroupFormBinding
import com.chunbae.narchive.databinding.ItemTodoRvTodoItemsFormBinding

class TodoWriteGroupAdapter (private val onClicked : (Int) -> Unit): RecyclerView.Adapter<TodoWriteGroupAdapter.TodoWriteGroupViewHolder>() {
    var groupList = mutableListOf<GroupData>()

    inner class TodoWriteGroupViewHolder(private val binding : ItemDialogWriteTodoGroupFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GroupData) {
            binding.groupData = item
            //:TODO 추후 todo index로 변경해야함
            binding.root.setOnClickListener { onClicked.invoke(absoluteAdapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoWriteGroupViewHolder {
        return TodoWriteGroupViewHolder(ItemDialogWriteTodoGroupFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = groupList.size

    override fun onBindViewHolder(holder: TodoWriteGroupViewHolder, position: Int) {
        holder.bind(groupList[position])
    }
}