package com.chunbae.narchive.presentation.ui.main.group.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.databinding.FragmentGroupContentBinding
import com.chunbae.narchive.presentation.ui.detail.book.DetailBookActivity
import com.chunbae.narchive.presentation.ui.detail.movie.DetailMovieActivity
import com.chunbae.narchive.presentation.ui.main.MainViewModel
import com.chunbae.narchive.presentation.ui.main.group.adapter.BookAdapter

class GroupContentFragment(private val type : String) : Fragment() {
    private lateinit var binding : FragmentGroupContentBinding
    private val viewModel : MainViewModel by activityViewModels()
    private val bookAdapter by lazy {
        BookAdapter(::onGroupItemClick)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupContentBinding.inflate(inflater, container, false)
        initRv()
        requestGroupData()

        return binding.root
    }

    private fun initRv() {
        when(type) {
            "Book" -> binding.fgGroupRvContents.adapter = bookAdapter
        }
    }

    private fun requestGroupData() {
        when(type) {
            "Book" -> {
                viewModel.getBookGroupData()
                viewModel.bookGroupData.observe(viewLifecycleOwner) {
                    bookAdapter.bookData = it
                    bookAdapter.notifyItemRangeChanged(0, it.size)
                }
            }
        }
    }

    private fun onGroupItemClick(type : String, itemId : Int) {
        val activity = if(type == "Book") DetailBookActivity::class.java else DetailMovieActivity::class.java

        val intent = Intent(requireContext(), activity)
        intent.putExtra("itemId", itemId)
        startActivity(intent)

    }
}