package com.chunbae.narchive.presentation.ui.detail.book.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ActivityBookMovieDetailBinding
import com.chunbae.narchive.presentation.ui.detail.book.adapter.DetailBookKeywordAdapter
import com.chunbae.narchive.presentation.ui.detail.book.viewmodel.DetailBookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookMovieDetailBinding
    private val viewModel: DetailBookViewModel by viewModels()
    private var isLoading = false
    private var isThumbnail = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_movie_detail)

        initBinding()
        getBookData()
        observe()
    }

    private fun initBinding() {
        binding.bookActivity = this
        binding.lifecycleOwner = this
        binding.type = "Book"
    }

    private fun getBookData() {
        viewModel.setBookPK(intent.getIntExtra("itemId", 0))
    }

    private fun observe() {
        viewModel.selectedBookData.observe(this) {
            binding.bookData = it

            it.keyword?.let { binding.detailBookMovieRvKeyword.adapter =
                DetailBookKeywordAdapter(it.split(",")) }

        }
    }
    fun bookCard(): View = binding.detailBookMovieLayoutThumbnail

    fun onImageCardClick(view: View) {
        if (!isLoading) {
            isLoading = true
            val anim = android.animation.ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
                .setDuration(400)
            anim.doOnEnd { on ->
                rotateMore(view)
                isLoading = false
            }
            anim.start()

        }
    }

    private fun rotateMore(view: View) {
        if (isThumbnail) {
            binding.detailBookMovieIvThumbnail.visibility = View.INVISIBLE
            binding.detailBookMovieIvUserImage.visibility = View.VISIBLE
        } else {
            binding.detailBookMovieIvThumbnail.visibility = View.VISIBLE
            binding.detailBookMovieIvUserImage.visibility = View.INVISIBLE
        }
        isThumbnail = !isThumbnail
        android.animation.ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
            .setDuration(400).start()
    }
}