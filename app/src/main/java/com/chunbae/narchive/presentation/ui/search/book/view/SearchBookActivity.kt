package com.chunbae.narchive.presentation.ui.search.book.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ActivityBookMovieSearchBinding
import com.chunbae.narchive.presentation.ui.search.book.adapter.SearchBookAdapter
import com.chunbae.narchive.presentation.ui.search.book.viewmodel.SearchBookViewModel
import com.chunbae.narchive.presentation.ui.write.book.view.WriteBookReviewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookMovieSearchBinding
    private val viewModel: SearchBookViewModel by viewModels()
    private val bookAdapter by lazy {
        SearchBookAdapter(::onBookSelected)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_movie_search)

        initBinding()
        initBookList()
        initBookObserver()
    }

    private fun initBinding() {
        binding.type = "Book"
        binding.bookActivity = this
        binding.bookViewModel = viewModel
    }

    private fun initBookList() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.searchRvContainer.adapter = bookAdapter
        binding.searchRvContainer.addItemDecoration(dividerItemDecoration)
    }

    fun searchBook() {
        if (binding.searchEdtUserKeyword.text.isNotEmpty()) viewModel.getSearchedBookData(binding.searchEdtUserKeyword.text.toString())
        else Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
    }

    private fun initBookObserver() {
        viewModel.searchedBookData.observe(this) {
            bookAdapter.bookItems = it
            bookAdapter.notifyDataSetChanged()
        }
    }

    private fun onBookSelected(item: BookData) {
        val intent = Intent(this, WriteBookReviewActivity::class.java)
        intent.putExtra("Book", item)
        startActivity(intent)
        finish()
    }
}