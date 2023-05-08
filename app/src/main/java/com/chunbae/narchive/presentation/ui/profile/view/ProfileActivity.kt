package com.chunbae.narchive.presentation.ui.profile.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivitySetProfileBinding
import com.chunbae.narchive.presentation.ui.profile.viewmodel.ProfileViewModel
import com.chunbae.narchive.presentation.ui.write.dialog.GalleryCameraDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySetProfileBinding
    private val viewModel : ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_set_profile)

        initBinding()
        observeBottomSheet()
        observeProfileFinished()
    }

    private fun initBinding() {
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun observeProfileFinished() {
        viewModel.userNickName.observe(this) {
            viewModel.setProfileFinished()
        }
        viewModel.userSelectedImage.observe(this) {
            viewModel.setProfileFinished()
        }

        viewModel.profileURL.observe(this) {
            viewModel.uploadToServer()
        }
    }

    private fun observeBottomSheet() {
        viewModel.isGalleryCameraDialogOpened.observe(this) {
            if(it) GalleryCameraDialog().show(supportFragmentManager, "Profile")
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
}