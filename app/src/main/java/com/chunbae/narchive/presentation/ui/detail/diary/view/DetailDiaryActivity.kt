package com.chunbae.narchive.presentation.ui.detail.diary.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.data.data.UserData
import com.chunbae.narchive.databinding.ActivityDetailDiaryBinding
import com.chunbae.narchive.presentation.ui.comment.view.DiaryCommentActivity
import com.chunbae.narchive.presentation.ui.detail.diary.adapter.DetailDiaryImageAdapter

class DetailDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_diary)

        initBinding()
        initImages()
    }

    private fun initBinding() {
        binding.activity = this
    }

    override fun onStart() {
        super.onStart()
        intent.getIntExtra("position", 0)
    }

    private fun initImages() {
//        binding.detailDiaryVpImage.adapter = DetailDiaryImageAdapter(returnImageDummy())
//        binding.detailDiaryTvImageIndicator.text = "1 / ${returnImageDummy().size}"
//        binding.detailDiaryVpImage.registerOnPageChangeCallback(object : OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                binding.detailDiaryTvImageIndicator.text = "${position + 1} / ${returnImageDummy().size}"
//            }
//        })
    }

    fun onCommentClick() {
        val intent = Intent(this, DiaryCommentActivity::class.java)
        //:TODO 수정하기
        intent.putExtra("contentIdx", 0)
        startActivity(intent)
    }
}