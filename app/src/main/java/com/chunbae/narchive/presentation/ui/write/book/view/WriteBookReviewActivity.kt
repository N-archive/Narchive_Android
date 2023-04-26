package com.chunbae.narchive.presentation.ui.write.book.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.data.KeywordData
import com.chunbae.narchive.databinding.ActivityWriteBookMovieReviewBinding
import com.chunbae.narchive.presentation.ui.write.book.adapter.WriteBookReviewKeywordAdapter
import com.chunbae.narchive.presentation.ui.write.book.viewmodel.WriteBookViewModel
import com.chunbae.narchive.presentation.util.ConvertUtil
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class WriteBookReviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWriteBookMovieReviewBinding
    private val viewModel : WriteBookViewModel by viewModels()
    private val keywordAdapter by lazy {
        WriteBookReviewKeywordAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_book_movie_review)

        getBookData()
        initBinding()
        initKeyword()
        observe()

    }

    private fun getBookData() = intent.getSerializableExtra("Book")

    fun keywordLayoutVisibility() {
       binding.writeBookMovieReviewRvKeywords.visibility = if (binding.writeBookMovieReviewRvKeywords.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
    fun reviewLayoutVisibility() {
        binding.writeBookMovieReviewEdtReview.visibility = if(binding.writeBookMovieReviewEdtReview.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    private fun initBinding() {
        binding.bookActivity = this
        binding.type = "Book"
        binding.lifecycleOwner = this
        binding.writeBookMovieReviewLayoutBookMovie.type = "Book"
        binding.writeBookMovieReviewLayoutBookMovie.bookData = getBookData() as BookData
    }

    private fun initKeyword() {
        viewModel.setItem()
        binding.writeBookMovieReviewRvKeywords.adapter = keywordAdapter
    }

    private fun observe() {
        viewModel.keywordItems.observe(this) {
            keywordAdapter.bookKeywordData = it
        }
    }

    fun onSaveReview() {
        Log.d("----", "onSaveReview: ${keywordAdapter.bookKeywordData}")
    }

    /** Dummy */

    /*private fun chipDummy(chipGroup : ChipGroup, items : List<KeywordData.Keywords>) {
        for (i in items) {
            chipGroup.addView(Chip(this).apply {
                this.isCheckable = true
                this.checkedIcon = null
                this.rippleColor = null
                this.text = i.itemTitle
                this.setTextColor(resources.getColorStateList(R.color.selector_chip_text, null))
                this.chipBackgroundColor = resources.getColorStateList(R.color.selector_chip_solid, null)
                this.chipStrokeColor = resources.getColorStateList(R.color.color_B2F0F4, null)
                this.chipCornerRadius = ConvertUtil.dpToPx(this@WriteBookReviewActivity, 5F)
                this.chipStrokeWidth = ConvertUtil.dpToPx(this@WriteBookReviewActivity, 1F)
                this.textStartPadding = ConvertUtil.dpToPx(this@WriteBookReviewActivity, 15F)
                this.textEndPadding = ConvertUtil.dpToPx(this@WriteBookReviewActivity, 15F)

                /*this.setOnClickListener {
                    if(this.isChecked) {
                        Log.d("----", "chipDummy: chk / ${this.text} / ${this.id}")
                    }

                }*/
            })
        }
    }*/
}