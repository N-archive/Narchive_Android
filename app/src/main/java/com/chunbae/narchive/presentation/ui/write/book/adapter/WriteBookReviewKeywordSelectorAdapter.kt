package com.chunbae.narchive.presentation.ui.write.book.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.KeywordData
import com.chunbae.narchive.databinding.ItemWriteBookMovieReviewKeywordsSelectorBinding

class WriteBookReviewKeywordSelectorAdapter(private val sendEvent : (Int, List<KeywordData.Keywords>) -> Unit, private val pos : Int, private val keywords : List<KeywordData.Keywords>) : RecyclerView.Adapter<WriteBookReviewKeywordSelectorAdapter.WriteBookReviewKeywordSelectorViewHolder>(){
    private var selectedPosition = -1
    inner class WriteBookReviewKeywordSelectorViewHolder(private val binding: ItemWriteBookMovieReviewKeywordsSelectorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KeywordData.Keywords) {
            binding.keywordItem = item
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
                sendEvent.invoke(pos, keywords)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WriteBookReviewKeywordSelectorViewHolder {
        return WriteBookReviewKeywordSelectorViewHolder(
            ItemWriteBookMovieReviewKeywordsSelectorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = keywords.size

    override fun onBindViewHolder(holder: WriteBookReviewKeywordSelectorViewHolder, position: Int) {
        holder.bind(keywords[position])
    }

    private fun onItemClick(position: Int){
        if (selectedPosition != position && selectedPosition != -1) {
            keywords[selectedPosition].isClicked = false
            notifyItemChanged(selectedPosition)
        }
        selectedPosition = position
        keywords[position].isClicked = true
        notifyItemChanged(position)
    }
}