package com.chunbae.narchive.presentation.ui.search.book.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ItemSearchBookMovieListFormBinding

class SearchBookAdapter(private val onItemClick : (BookData) -> Unit) : RecyclerView.Adapter<SearchBookAdapter.SearchBookViewHolder>() {
    var bookItems = mutableListOf<BookData>()

    inner class SearchBookViewHolder(private val binding : ItemSearchBookMovieListFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BookData) {
            binding.type = "Book"
            binding.bookData = item
            binding.root.setOnClickListener { onItemClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBookViewHolder {
        return SearchBookViewHolder(ItemSearchBookMovieListFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = bookItems.size

    override fun onBindViewHolder(holder: SearchBookViewHolder, position: Int) {
        holder.bind(bookItems[position])
    }
}