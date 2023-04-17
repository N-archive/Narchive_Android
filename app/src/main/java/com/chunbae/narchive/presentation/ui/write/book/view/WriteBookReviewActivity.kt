package com.chunbae.narchive.presentation.ui.write.book.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ActivityWriteBookMovieReviewBinding

class WriteBookReviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWriteBookMovieReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_book_movie_review)

        getBookData()
        initBinding()

    }

    private fun getBookData() = intent.getSerializableExtra("Book")

    fun keywordLayoutVisibility() {
       binding.test.visibility = if(binding.test.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    private fun initBinding() {
        binding.bookActivity = this
        binding.type = "Book"
        binding.writeBookMovieReviewLayoutBookMovie.type = "Book"
        binding.writeBookMovieReviewLayoutBookMovie.bookData = getBookData() as BookData
    }
}