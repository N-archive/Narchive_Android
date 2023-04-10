package com.chunbae.narchive.presentation.ui.main.feed.view

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.data.data.UserData
import com.chunbae.narchive.databinding.FragmentFeedBinding
import com.chunbae.narchive.presentation.ui.detail.diary.view.DetailDiaryActivity
import com.chunbae.narchive.presentation.ui.main.feed.adapter.FeedAdapter
import com.chunbae.narchive.presentation.util.ConvertUtil

class FeedFragment : Fragment() {
    private lateinit var binding : FragmentFeedBinding
    private val feedAdapter by lazy {
        FeedAdapter(::onFeedItemClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)

        initBinding()
        initFeed()

        return binding.root
    }

    private fun initBinding() {
        binding.fragment = this
    }

    private fun initFeed() {
        binding.fgFeedRvFeed.adapter = feedAdapter
        binding.fgFeedRvFeed.addItemDecoration(FeedRVDecorUtil(requireActivity()))

        feedAdapter.feedDatas = getFeedDummy()
    }

    fun onSearchClicked() {
        //:TODO 검색버튼 클릭시 동작 로직 추가하기
    }

    fun onFeedItemClicked(position : Int) {
        val intent = Intent(requireActivity(), DetailDiaryActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    inner class FeedRVDecorUtil(private val context : Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = ConvertUtil.dpToPx(context, 15)
            outRect.right = ConvertUtil.dpToPx(context, 15)
            outRect.bottom = ConvertUtil.dpToPx(context, 15)
        }
    }


    /** Dummy */

    private fun getFeedDummy() : MutableList<FeedData> =
        mutableListOf<FeedData>().apply {
            add(FeedData(UserData(0, R.drawable.ic_launcher_background, "테스트1"), "1일전", "2023.03.1", "본문내용입니다!", R.drawable.ic_launcher_background, 1, "양천구 신정중앙로 1", "1", null, "F"))
            add(FeedData(UserData(1, R.drawable.ic_launcher_background, "테스트2"), "2일전", "2023.03.2", null, R.drawable.ic_launcher_background, 2, "양천구 신정중앙로 2", "2", getKeywordDummy(), "T"))
            add(FeedData(UserData(2, R.drawable.ic_launcher_background, "테스트3"), "3일전", "2023.03.3","본문내용입니다!", R.drawable.ic_launcher_background, 3, "양천구 신정중앙로 3", "3", null, "F"))
            add(FeedData(UserData(3, R.drawable.ic_launcher_background, "테스트4"), "4일전", "2023.03.4","본문내용입니다!", R.drawable.ic_launcher_background, 4, "양천구 신정중앙로 4", "4", getKeywordDummy(), "T"))
        }

    private fun getKeywordDummy() : List<String> = listOf("1","2","3","4","5")
}