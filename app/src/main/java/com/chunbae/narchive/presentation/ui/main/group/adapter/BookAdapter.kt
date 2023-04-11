package com.chunbae.narchive.presentation.ui.main.group.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ItemGroupContentFormBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    var bookData = mutableListOf<BookData>()
    inner class BookViewHolder(private val binding : ItemGroupContentFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BookData) {
            binding.type = "Book"
            binding.bookData = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(ItemGroupContentFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = bookData.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookData[position])
    }


}