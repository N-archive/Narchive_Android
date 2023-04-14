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

    private lateinit var binding : ActivityDiaryCommentBinding
    private val commentAdapter by lazy {
        CommentAdapter(getCommentDummies())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diary_comment)

        initBinding()
        initComment()
    }

    private fun initBinding() {
        binding.commentData = getCommentDummy()
    }

    private fun initComment() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.diaryCommentRvComments.addItemDecoration(dividerItemDecoration)
        binding.diaryCommentRvComments.adapter = commentAdapter

    }


    /** Dummy */

    private fun getCommentDummy() : CommentData =
        CommentData(UserData(0, R.drawable.ic_launcher_background, "춘배"), "3일 전", "어제는 지연이와 함께 찜질방에 다녀왔다!\n요근래 찜질방에 갔던적이 없었는데 역시 따뜻한 물에 들어가있으니까\n" +
                "피로가 쫙 풀리는 기분이었다!\n근데 음식값이 생각보다 비싸서 그건 좀 아쉬웠다..\n" +
                "계란이 개당 천원꼴에 치킨이 3만원이라니..\n" +
                "그나마 식혜가 제일 합리적이었다\n그래도 음식 가격만 제외하면 정말 잘 놀다 왔다!\n대만족!", getCommentDummies())

    private fun getCommentDummies() : MutableList<Comment> =
        mutableListOf<Comment>().apply {
            for(i in 0 until 10) {
                add(Comment(i, UserData(0, R.drawable.ic_launcher_background, "춘배"), "$i 번째 댓글", "$i 일 전"))
            }
        }
}