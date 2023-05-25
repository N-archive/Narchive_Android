package com.chunbae.narchive.presentation.ui.detail.diary.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
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
import com.chunbae.narchive.presentation.ui.detail.diary.viewmodel.DetailDiaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDiaryBinding
    private val viewModel : DetailDiaryViewModel by viewModels()
    private val detailAdapter by lazy {
        DetailDiaryImageAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_diary)

        initBinding()
        initGetDiaryDetail()
        initVP()
        observeDetailData()
    }

    private fun initBinding() {
        binding.activity = this
        binding.lifecycleOwner = this
    }

    private fun initGetDiaryDetail() {
        viewModel.getDiaryDetailData(intent.getIntExtra("position", 0))
    }

    private fun initVP() {
        binding.detailDiaryVpImage.adapter = detailAdapter
        binding.detailDiaryVpImage.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.detailDiaryTvImageIndicator.text = "${position + 1} / ${detailAdapter.imageList.size}"
            }
        })
    }

    private fun observeDetailData() {
        viewModel.diaryDetailData.observe(this) {
            binding.diaryData = it
            it.images?.let { list -> detailAdapter.imageList = list
            detailAdapter.notifyItemRangeInserted(0, list.size)}
        }
    }



    fun onCommentClick() {
        val intent = Intent(this, DiaryCommentActivity::class.java)
        intent.putExtra("contentIdx", 0)
        startActivity(intent)
    }
}