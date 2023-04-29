package com.chunbae.narchive.presentation.ui.write.book.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.databinding.ActivityWriteBookMovieReviewBinding
import com.chunbae.narchive.presentation.ui.write.book.adapter.WriteBookReviewKeywordAdapter
import com.chunbae.narchive.presentation.ui.write.book.viewmodel.WriteBookViewModel
import com.chunbae.narchive.presentation.ui.write.dialog.GalleryCameraDialog

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
        observeBottomSheet()

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
        binding.bookViewModel = viewModel
        binding.lifecycleOwner = this
        binding.type = "Book"
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

    private fun observeBottomSheet() {
        viewModel.isGalleryCameraDialogOpened.observe(this) {
            if(it) GalleryCameraDialog().show(supportFragmentManager, "Book")
        }

        viewModel.isGalleryOrCamera.observe(this) {
            Log.d("----", "observeBottomSheet: $it")
            when(it) {
                0 -> onCameraOpen()
                1 -> onGalleryOpen()
            }
        }
    }

    private fun onCameraOpen() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { it1 ->
            it1.resolveActivity(packageManager)?.also {
                this.requestCameraActivity.launch(it1)
            }
        }
    }

    private fun onGalleryOpen() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        this.requestGalleryActivity.launch(intent)

        viewModel.initGalleryOrCamera()
    }

    private val requestCameraActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val imageBitmap = it.data!!.extras?.get("data") as Bitmap
                Log.d("----", "$imageBitmap: ")
                viewModel.setUserSelectedImage(imageBitmap)
            }
        }


    private val requestGalleryActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data?.data != null) { //갤러리 캡쳐 결과값
                val clipData = it?.data?.clipData
                if (clipData == null) {
                    it.data!!.data?.let { it1 -> viewModel.setUserSelectedImage(it1) }
                }
            }
        }

    /** Dummy */

}