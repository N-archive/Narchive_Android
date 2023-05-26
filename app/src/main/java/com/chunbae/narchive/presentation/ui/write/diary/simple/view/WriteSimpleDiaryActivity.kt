package com.chunbae.narchive.presentation.ui.write.diary.simple.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.databinding.ActivityWriteDiarySimpleBinding
import com.chunbae.narchive.presentation.ui.gallery.view.CustomGalleryActivity
import com.chunbae.narchive.presentation.ui.search.location.view.SearchLocationActivity
import com.chunbae.narchive.presentation.ui.write.diary.simple.adapter.WriteSimpleDiaryImageAdapter
import com.chunbae.narchive.presentation.ui.write.diary.simple.viewmodel.WriteSimpleDiaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteSimpleDiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWriteDiarySimpleBinding
    private val viewModel : WriteSimpleDiaryViewModel by viewModels()

    private val imageAdapter by lazy {
        WriteSimpleDiaryImageAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_diary_simple)

        initBinding()
        initRV()
        observe()
    }

    private fun initBinding() {
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initRV() {
        binding.writeDiarySimpleRvImages.adapter = imageAdapter
    }

    private fun observe() {
        observeImage()
        observeKeyword()
        observePostDiary()
    }

    private fun observeKeyword() {
        viewModel.userInputContent.observe(this) {
            viewModel.convertKeywordsToList()
        }
    }

    private fun observeImage() {
        viewModel.selectedImages.observe(this) {
            imageAdapter.imageList = it
            imageAdapter.notifyDataSetChanged()
        }
    }

    private fun observePostDiary() {
        viewModel.diaryState.observe(this) {
            finish()
        }
    }

    fun getLocation() {
        val intent = Intent(this, SearchLocationActivity::class.java)
        this.requestLocationActivity.launch(intent)
    }

    private val requestLocationActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                viewModel.setLocation(it.data?.extras?.getSerializable("Location") as LocationData)
            }
        }

    fun openGalleryActivity() {
        val intent = Intent(this, CustomGalleryActivity::class.java)
        this.requestGalleryActivity.launch(intent)
    }

    private val requestGalleryActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                viewModel.setImages(it.data?.extras?.getStringArrayList("Images") as MutableList<String>)
            }
        }


}