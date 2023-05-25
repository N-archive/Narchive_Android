package com.chunbae.narchive.presentation.ui.detail.diary.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.databinding.ItemDiaryDetailImageviewBinding

class DetailDiaryImageAdapter() : RecyclerView.Adapter<DetailDiaryImageAdapter.DetailDiaryImageViewHolder>(){
    var imageList = mutableListOf<String>()
    inner class DetailDiaryImageViewHolder(private val binding : ItemDiaryDetailImageviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            binding.imagePath = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailDiaryImageViewHolder {
        return DetailDiaryImageViewHolder(ItemDiaryDetailImageviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: DetailDiaryImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }
}