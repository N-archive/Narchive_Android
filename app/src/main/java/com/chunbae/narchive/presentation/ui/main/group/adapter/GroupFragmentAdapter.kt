package com.chunbae.narchive.presentation.ui.main.group.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chunbae.narchive.presentation.ui.main.group.view.GroupContentFragment

class GroupFragmentAdapter(fm : FragmentActivity) : FragmentStateAdapter(fm) {
    val PAGE_NUM = 3

    override fun getItemCount(): Int = PAGE_NUM

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> GroupContentFragment("Book")
            1 -> GroupContentFragment("Movie")
            else -> GroupContentFragment("Location")
        }
    }
}