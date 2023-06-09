package com.chunbae.narchive.presentation.ui.search.movie.view

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
import com.chunbae.narchive.data.remote.response.ResponseMovieData
import com.chunbae.narchive.databinding.ActivityBookMovieSearchBinding
import com.chunbae.narchive.presentation.ui.search.movie.adapter.SearchMovieAdapter
import com.chunbae.narchive.presentation.ui.search.movie.viewmodel.SearchMovieViewModel
import com.chunbae.narchive.presentation.ui.write.book.view.WriteBookReviewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookMovieSearchBinding
    private val viewModel : SearchMovieViewModel by viewModels()
    private val movieAdapter by lazy {
        SearchMovieAdapter(::onMovieSelected)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_movie_search)

        initBinding()
        initMovieList()
        initMovieObserver()
    }

    private fun initBinding() {
        binding.type = "Movie"
        binding.movieActivity = this
        binding.movieViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initMovieList() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.searchRvContainer.adapter = movieAdapter
        binding.searchRvContainer.addItemDecoration(dividerItemDecoration)
    }

    private fun initMovieObserver() {
        viewModel.searchedMovieList.observe(this) {
            Log.d("----", "initMovieObserver: $it")
            movieAdapter.movieItems = it
            movieAdapter.notifyItemRangeChanged(0, it.size)
        }
    }

    fun searchMovie() {
        if(binding.searchEdtUserKeyword.text.isNotEmpty()) viewModel.getSearchedMovieData(binding.searchEdtUserKeyword.text.toString())
        else Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
    }

    private fun onMovieSelected(item: ResponseMovieData.ResultSearchMovie) {
        val intent = Intent(this, WriteBookReviewActivity::class.java)
        intent.putExtra("Movie", item.movieCd)
        startActivity(intent)
        finish()
    }
}