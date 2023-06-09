package com.chunbae.narchive.presentation.ui.search.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.remote.response.ResponseMovieData
import com.chunbae.narchive.databinding.ItemSearchBookMovieListFormBinding
import com.chunbae.narchive.presentation.ui.search.book.adapter.SearchBookAdapter

class SearchMovieAdapter(private val onItemClick : (ResponseMovieData.ResultSearchMovie) -> Unit) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {
    var movieItems = mutableListOf<ResponseMovieData.ResultSearchMovie>()

    inner class SearchMovieViewHolder(private val binding : ItemSearchBookMovieListFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResponseMovieData.ResultSearchMovie) {
            binding.type = "Movie"
            binding.movieData = item
            binding.root.setOnClickListener { onItemClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(
            ItemSearchBookMovieListFormBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }
}