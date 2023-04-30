package com.chunbae.narchive.presentation.ui.write.diary.normal.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.databinding.ItemWriteDiaryImageFormBinding

class WriteNormalDiaryImageAdapter : RecyclerView.Adapter<WriteNormalDiaryImageAdapter.WriteNormalDiaryImageViewHolder>() {
    var imageList = mutableListOf<String>()

    inner class WriteNormalDiaryImageViewHolder(private val binding : ItemWriteDiaryImageFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            Log.d("----", "bind: $item")
            binding.path = item.toUri()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WriteNormalDiaryImageViewHolder {
        return WriteNormalDiaryImageViewHolder(ItemWriteDiaryImageFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: WriteNormalDiaryImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }
}