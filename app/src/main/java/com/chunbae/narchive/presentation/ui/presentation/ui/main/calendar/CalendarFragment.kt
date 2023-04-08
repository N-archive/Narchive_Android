package com.chunbae.narchive.presentation.ui.presentation.ui.main.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {
    private lateinit var binding : FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        return binding.root
    }
}