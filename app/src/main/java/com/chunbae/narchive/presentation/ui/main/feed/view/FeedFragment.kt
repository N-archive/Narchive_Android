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
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.data.data.UserData
import com.chunbae.narchive.databinding.FragmentFeedBinding
import com.chunbae.narchive.presentation.ui.detail.diary.view.DetailDiaryActivity
import com.chunbae.narchive.presentation.ui.main.MainViewModel
import com.chunbae.narchive.presentation.ui.main.feed.adapter.FeedAdapter
import com.chunbae.narchive.presentation.util.ConvertUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private lateinit var binding : FragmentFeedBinding
    private val viewModel : MainViewModel by activityViewModels()
    private val feedAdapter by lazy {
        FeedAdapter(::onFeedItemClicked)
    }
    val DEFAULT_PAGE = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)

        initBinding()
        initFeed()
        observeFeed()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFeedData(DEFAULT_PAGE)
    }

    private fun initBinding() {
        binding.fragment = this
        binding.viewModel = viewModel
    }

    private fun initFeed() {
        binding.fgFeedRvFeed.adapter = feedAdapter
        binding.fgFeedRvFeed.itemAnimator = null
        binding.fgFeedRvFeed.addItemDecoration(FeedRVDecorUtil(requireActivity()))
    }

    private fun observeFeed() {
        viewModel.feedDiaryData.observe(viewLifecycleOwner) {
            feedAdapter.feedDatas = it
            feedAdapter.notifyItemRangeChanged(0, it.size)
        }
    }

    fun onSearchClicked() {
        //:TODO 검색버튼 클릭시 동작 로직 추가하기
    }

    private fun onFeedItemClicked(position : Int) {
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
}