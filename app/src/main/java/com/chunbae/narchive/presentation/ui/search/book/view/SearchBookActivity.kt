package com.chunbae.narchive.presentation.ui.search.book.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ActivityBookMovieSearchBinding
import com.chunbae.narchive.presentation.ui.search.book.adapter.SearchBookAdapter

class SearchBookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookMovieSearchBinding
    private val bookAdapter by lazy {
        SearchBookAdapter(::onBookSelected)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_movie_search)

        initBinding()
        initBookList()
    }

    private fun initBinding() {
        binding.type = "Book"
    }

    private fun initBookList() {
        binding.searchRvContainer.adapter = bookAdapter
    }

    private fun onBookSelected(item : BookData) {

    }
}