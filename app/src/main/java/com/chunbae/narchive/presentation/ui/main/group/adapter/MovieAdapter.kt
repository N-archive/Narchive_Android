package com.chunbae.narchive.presentation.ui.main.group.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.data.MovieData
import com.chunbae.narchive.databinding.ItemGroupContentFormBinding

class MovieAdapter(private val onClick : (String, Int) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movieData = mutableListOf<MovieData>()

    inner class MovieViewHolder(private val binding : ItemGroupContentFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MovieData) {
            binding.type = "Movie"
            binding.movieData = item
            binding.root.setOnClickListener { onClick.invoke("Movie", item.movieId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemGroupContentFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = movieData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieData[position])
    }


}