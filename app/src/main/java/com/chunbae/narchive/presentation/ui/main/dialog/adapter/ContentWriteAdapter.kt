package com.chunbae.narchive.presentation.ui.main.dialog.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.WriteDialogData
import com.chunbae.narchive.databinding.ItemDialogContentWriteSelectorFormBinding

class ContentWriteAdapter(private val onItemClick : (Int) -> Unit, private val items : List<WriteDialogData>) : RecyclerView.Adapter<ContentWriteAdapter.ContentWriteViewHolder>() {

    inner class ContentWriteViewHolder(private val binding : ItemDialogContentWriteSelectorFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : WriteDialogData) {
            binding.writeDialogData = item
            binding.root.setOnClickListener { onItemClick.invoke(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentWriteViewHolder {
        return ContentWriteViewHolder(ItemDialogContentWriteSelectorFormBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContentWriteViewHolder, position: Int) {
        holder.bind(items[position])
    }
}