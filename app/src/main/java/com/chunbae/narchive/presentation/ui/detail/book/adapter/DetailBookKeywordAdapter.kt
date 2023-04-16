package com.chunbae.narchive.presentation.ui.detail.book.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.databinding.ItemBookMovieDetailKeywordsBinding

class DetailBookKeywordAdapter(private val keywords : List<String>?) : RecyclerView.Adapter<DetailBookKeywordAdapter.DetailBookKeywordViewHolder>() {

    inner class DetailBookKeywordViewHolder(private val binding : ItemBookMovieDetailKeywordsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String?) {
            item.let { binding.keyword = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBookKeywordViewHolder {
        return DetailBookKeywordViewHolder(ItemBookMovieDetailKeywordsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = keywords?.size ?: 0

    override fun onBindViewHolder(holder: DetailBookKeywordViewHolder, position: Int) {
        keywords.let { holder.bind(it?.get(position)) }
    }
}