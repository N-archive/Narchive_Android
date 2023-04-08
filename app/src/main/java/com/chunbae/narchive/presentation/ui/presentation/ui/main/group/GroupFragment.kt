package com.chunbae.narchive.presentation.ui.presentation.ui.main.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.FragmentGroupBinding

class GroupFragment : Fragment() {
    private lateinit var binding : FragmentGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group, container, false)
        return binding.root
    }
}