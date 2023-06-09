package com.chunbae.narchive.presentation.ui.todogroup.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.databinding.ItemManageTodoGroupRvGroupBinding
import com.chunbae.narchive.presentation.ui.todogroup.adapter.util.ItemTouchHelperListener

class TodoGroupAdapter(private val onDelete : (Int) -> Unit, private val onEdit : (String) -> Unit, private val setEditGroup : (GroupData) -> Unit): RecyclerView.Adapter<TodoGroupAdapter.TodoGroupViewHolder>(), ItemTouchHelperListener {
    var groupList = mutableListOf<GroupData>()
    inner class TodoGroupViewHolder(private val binding : ItemManageTodoGroupRvGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GroupData) {
            binding.groupData = item

            binding.root.setOnLongClickListener {
                setEditGroup.invoke(item)
                onEdit.invoke("EDIT")
            return@setOnLongClickListener false }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoGroupViewHolder {
        return TodoGroupViewHolder(ItemManageTodoGroupRvGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = groupList.size

    override fun onBindViewHolder(holder: TodoGroupViewHolder, position: Int) {
        holder.bind(groupList[position])
    }


    override fun onItemSwipe(position: Int) {
        groupList[position].todoGroupIdx?.let { onDelete.invoke(it) }
    }
}