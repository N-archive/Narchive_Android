package com.chunbae.narchive.presentation.ui.write.diary.normal.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivityWriteDiaryNormalBinding
import com.chunbae.narchive.presentation.ui.search.location.view.SearchLocationActivity

class WriteNormalDiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWriteDiaryNormalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_diary_normal)

        initBinding()
    }

    private fun initBinding() {
        binding.activity = this
    }

    fun getLocation() {
        val intent = Intent(this, SearchLocationActivity::class.java)
        this.requestLocationActivity.launch(intent)
    }

    private val requestLocationActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val location = it.data?.extras?.getSerializable("Location")
                Log.d("----", "$location: ")
            }
        }
}