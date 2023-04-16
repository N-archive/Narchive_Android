package com.chunbae.narchive.presentation.ui.detail.movie

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivityBookMovieDetailBinding

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_movie_detail)
    }

    fun onImageCardClick() {

    }

    fun movieCard() : View = binding.detailBookMovieLayoutThumbnail

    fun onImageCardClick(view : View) {

    }
}