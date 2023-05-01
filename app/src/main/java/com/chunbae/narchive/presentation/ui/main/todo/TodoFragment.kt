package com.chunbae.narchive.presentation.ui.main.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.FragmentTodoBinding
import com.chunbae.narchive.presentation.ui.main.MainViewModel

class TodoFragment : Fragment() {
    private lateinit var binding : FragmentTodoBinding
    private val viewModel : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setCalClickedFalse()
    }
}