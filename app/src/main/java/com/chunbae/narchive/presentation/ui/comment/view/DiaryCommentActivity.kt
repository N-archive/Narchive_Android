package com.chunbae.narchive.presentation.ui.comment.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.Comment
import com.chunbae.narchive.data.data.CommentData
import com.chunbae.narchive.data.data.UserData
import com.chunbae.narchive.data.data.WriteDialogData
import com.chunbae.narchive.data.data.returnWriteDialogDataList
import com.chunbae.narchive.databinding.ActivityDiaryCommentBinding
import com.chunbae.narchive.presentation.ui.comment.adapter.CommentAdapter
import com.chunbae.narchive.presentation.ui.comment.viewmodel.DiaryCommentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryCommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryCommentBinding
    private val viewModel : DiaryCommentViewModel by viewModels()
    private val commentAdapter by lazy {
        CommentAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diary_comment)

        getDiaryIdx()
        observeCommentData()
        initComment()
    }

    private fun getDiaryIdx() {
        viewModel.getCommentData(intent.getIntExtra("diaryIdx", 0))
    }

    private fun observeCommentData() {
        viewModel.commentData.observe(this) {
            binding.commentData = it
            it.commentList?.let {commentAdapter.comments = it
                commentAdapter.notifyItemRangeChanged(0, it.size)}
        }
    }

    private fun initComment() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.diaryCommentRvComments.addItemDecoration(dividerItemDecoration)
        binding.diaryCommentRvComments.adapter = commentAdapter
    }
}