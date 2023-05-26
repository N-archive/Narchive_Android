package com.chunbae.narchive.presentation.ui.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.Comment
import com.chunbae.narchive.databinding.ItemDiaryCommentFormBinding

class CommentAdapter() : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    var comments =  listOf<Comment>()
    inner class CommentViewHolder(private val binding : ItemDiaryCommentFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Comment) {
            binding.comment = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ItemDiaryCommentFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }
}