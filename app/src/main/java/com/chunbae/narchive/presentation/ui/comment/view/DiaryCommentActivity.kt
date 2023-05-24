package com.chunbae.narchive.presentation.ui.comment.view

import android.os.Bundle
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


class DiaryCommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryCommentBinding
    private val commentAdapter by lazy {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diary_comment)

        initBinding()
        initComment()
    }

    private fun initBinding() {

    }

    private fun initComment() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.diaryCommentRvComments.addItemDecoration(dividerItemDecoration)
        // binding.diaryCommentRvComments.adapter = commentAdapter

    }
}