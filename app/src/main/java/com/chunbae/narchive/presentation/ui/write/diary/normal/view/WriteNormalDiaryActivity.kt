package com.chunbae.narchive.presentation.ui.write.diary.normal.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.databinding.ActivityWriteDiaryNormalBinding
import com.chunbae.narchive.presentation.ui.gallery.view.CustomGalleryActivity
import com.chunbae.narchive.presentation.ui.search.location.view.SearchLocationActivity
import com.chunbae.narchive.presentation.ui.write.diary.normal.adapter.WriteNormalDiaryImageAdapter
import com.chunbae.narchive.presentation.ui.write.diary.normal.viewmodel.WriteNormalDiaryViewModel

class WriteNormalDiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWriteDiaryNormalBinding
    private val viewModel : WriteNormalDiaryViewModel by viewModels()
    private val imageAdapter by lazy {
        WriteNormalDiaryImageAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_diary_normal)

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
        binding.writeDiaryNormalRvImages.adapter = imageAdapter
    }

    private fun observe() {
        viewModel.selectedImages.observe(this) {
            imageAdapter.imageList = it
            imageAdapter.notifyDataSetChanged()
        }
    }

    fun openGalleryActivity() {
        val intent = Intent(this, CustomGalleryActivity::class.java)
        this.requestGalleryActivity.launch(intent)
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

    private val requestGalleryActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                viewModel.setImages(it.data?.extras?.getStringArrayList("Images") as MutableList<String>)
            }
        }
}