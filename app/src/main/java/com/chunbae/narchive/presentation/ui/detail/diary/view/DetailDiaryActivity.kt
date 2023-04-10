package com.chunbae.narchive.presentation.ui.detail.diary.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivityDetailDiaryBinding

class DetailDiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_diary)
    }

}