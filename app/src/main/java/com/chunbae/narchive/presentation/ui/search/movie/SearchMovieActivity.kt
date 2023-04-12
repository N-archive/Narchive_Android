package com.chunbae.narchive.presentation.ui.search.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivityBookMovieSearchBinding

class SearchMovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookMovieSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_movie_search)

        initBinding()
    }

    private fun initBinding() {
        binding.type = "Movie"
    }
}