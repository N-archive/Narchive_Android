package com.chunbae.narchive.presentation.ui.write.diary.normal.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivityWriteDiaryNormalBinding

class WriteNormalDiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWriteDiaryNormalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_diary_normal)
    }
}