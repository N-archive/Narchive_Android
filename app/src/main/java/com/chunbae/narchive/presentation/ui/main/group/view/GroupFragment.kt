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
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.data.MovieData
import com.chunbae.narchive.databinding.FragmentGroupBinding
import com.chunbae.narchive.presentation.ui.detail.book.DetailBookActivity
import com.chunbae.narchive.presentation.ui.detail.movie.DetailMovieActivity
import com.chunbae.narchive.presentation.ui.main.MainActivity
import com.chunbae.narchive.presentation.ui.main.MainViewModel
import com.chunbae.narchive.presentation.ui.main.group.adapter.BookAdapter
import com.chunbae.narchive.presentation.ui.main.group.adapter.MovieAdapter

class GroupFragment : Fragment() {
    private lateinit var binding : FragmentGroupBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group, container, false)

        initBinding()
        initGroup()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initGroup() {
        viewModel.isBookOrMovie.observe(viewLifecycleOwner) {
            binding.fgGroupTvGroupType.text = when(it) {
                "Book" -> "책"
                else -> "영화"
            }

            binding.fgGroupRvContents.adapter = when(it) {
                "Book" -> BookAdapter(::onGroupItemClick).also { it.bookData = returnBookData() }
                else -> MovieAdapter(::onGroupItemClick).also { it.movieData = returnMovieData() }
            }
        }
    }

    private fun onGroupItemClick(type : String, itemId : Int) {
        val activity = if(type == "Book") DetailBookActivity::class.java else DetailMovieActivity::class.java

        val intent = Intent(requireContext(), activity)
        intent.putExtra("itemId", itemId)
        startActivity(intent)

    }

    /** Dummy */

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