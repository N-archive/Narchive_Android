package com.chunbae.narchive.presentation.ui.main.group.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.data.MovieData
import com.chunbae.narchive.databinding.FragmentGroupBinding
import com.chunbae.narchive.presentation.ui.detail.book.DetailBookActivity
import com.chunbae.narchive.presentation.ui.detail.movie.DetailMovieActivity
import com.chunbae.narchive.presentation.ui.main.MainActivity
import com.chunbae.narchive.presentation.ui.main.MainViewModel
import com.chunbae.narchive.presentation.ui.main.group.adapter.BookAdapter
import com.chunbae.narchive.presentation.ui.main.group.adapter.GroupFragmentAdapter
import com.chunbae.narchive.presentation.ui.main.group.adapter.MovieAdapter
import com.google.android.material.tabs.TabLayoutMediator

class GroupFragment : Fragment() {
    private lateinit var binding : FragmentGroupBinding
    private val viewModel : MainViewModel by activityViewModels()
    private val groupFragmentAdapter by lazy {
        GroupFragmentAdapter(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group, container, false)

        initBinding()
        initTab()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initTab() {
        binding.fgGroupVpContents.apply {
            adapter = groupFragmentAdapter
            this.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            TabLayoutMediator(binding.fgGroupTabGroupType, this) { mTab, position ->
                mTab.text = setTabTitles()[position]
            }.attach()
            offscreenPageLimit = 3
        }
    }

    /** Dummy */

    private fun setTabTitles() : List<String> = listOf("책", "영화", "장소")

    private fun returnBookData() : MutableList<BookData> =
        mutableListOf<BookData>().apply {
            for(i in 0 until 10) {
                add(BookData(i, "123", R.drawable.ic_launcher_foreground, "$i 번째 책", i.toString(), i.toString(), i.toString(), 1.0F, null, null))
            }
        }

    private fun returnMovieData() : MutableList<MovieData> =
        mutableListOf<MovieData>().apply {
            for(i in 0 until 10) {
                add(MovieData(i, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, "$i 번째 영화", i.toString(), i.toString(), i.toString(), 1.0F, null, null))
            }
        }
}