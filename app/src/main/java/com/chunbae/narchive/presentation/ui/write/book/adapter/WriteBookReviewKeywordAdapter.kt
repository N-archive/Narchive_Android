package com.chunbae.narchive.presentation.ui.write.book.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.KeywordData
import com.chunbae.narchive.databinding.ItemWriteBookMovieReviewKeywordsBinding
import com.google.android.material.chip.ChipGroup

class WriteBookReviewKeywordAdapter() :
    RecyclerView.Adapter<WriteBookReviewKeywordAdapter.WriteBookReviewKeywordViewHolder>() {
    var bookKeywordData = mutableListOf<KeywordData>()

    inner class WriteBookReviewKeywordViewHolder(private val binding: ItemWriteBookMovieReviewKeywordsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KeywordData) {
            binding.keywordData = item
            binding.itemWriteBookMovieReviewKeywordsChipSelector.hasFixedSize()
            binding.itemWriteBookMovieReviewKeywordsChipSelector.adapter = item.keywordItems?.let { WriteBookReviewKeywordSelectorAdapter(::notifyClick, adapterPosition, it) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WriteBookReviewKeywordViewHolder {
        return WriteBookReviewKeywordViewHolder(
            ItemWriteBookMovieReviewKeywordsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = bookKeywordData.size

    override fun onBindViewHolder(holder: WriteBookReviewKeywordViewHolder, position: Int) {
        holder.bind(bookKeywordData[position])
    }

    private fun notifyClick(position : Int, data : List<KeywordData.Keywords>) {
        bookKeywordData[position].keywordItems = data
    }
}