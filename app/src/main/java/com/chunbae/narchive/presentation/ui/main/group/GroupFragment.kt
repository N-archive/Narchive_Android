package com.chunbae.narchive.presentation.ui.main.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.FragmentGroupBinding
import com.chunbae.narchive.presentation.ui.main.MainViewModel

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

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}