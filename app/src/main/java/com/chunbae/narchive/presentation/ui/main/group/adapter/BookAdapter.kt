package com.chunbae.narchive.presentation.ui.main.group.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.remote.response.ResponseBookGroupData
import com.chunbae.narchive.databinding.ItemGroupContentFormBinding

class BookAdapter(private val onClick : (String, Int) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    var bookData = mutableListOf<ResponseBookGroupData.ResponseBookGroupDataResult>()
    inner class BookViewHolder(private val binding : ItemGroupContentFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResponseBookGroupData.ResponseBookGroupDataResult) {
            binding.type = "Book"
            binding.bookData = item
            binding.root.setOnClickListener { onClick.invoke("Book", item.userReviewIdx!!) }
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