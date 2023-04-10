package com.chunbae.narchive.presentation.ui.main.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.databinding.ItemFeedSimpleDiaryRvKeywordsBinding

class SimpleDiaryKeywordAdapter(private val keywords : List<String>) : RecyclerView.Adapter<SimpleDiaryKeywordAdapter.KeywordViewHolder>() {

    inner class KeywordViewHolder(private val binding : ItemFeedSimpleDiaryRvKeywordsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            binding.keywords = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(ItemFeedSimpleDiaryRvKeywordsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = keywords.size

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(keywords[position])
    }
}