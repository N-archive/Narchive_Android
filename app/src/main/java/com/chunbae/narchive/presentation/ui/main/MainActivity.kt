package com.chunbae.narchive.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivityMainBinding
import com.chunbae.narchive.presentation.ui.main.calendar.CalendarFragment
import com.chunbae.narchive.presentation.ui.main.feed.view.FeedFragment
import com.chunbae.narchive.presentation.ui.main.group.GroupFragment
import com.chunbae.narchive.presentation.ui.main.settings.SettingsFragment
import com.chunbae.narchive.presentation.ui.main.todo.TodoFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
    }

    private fun initView() {
        initBottomNav()
    }

    private fun initBottomNav() {
        binding.mainBottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.main_bottom_nav_calendar -> changeFragment(CalendarFragment())
                R.id.main_bottom_nav_todo -> changeFragment(TodoFragment())
                R.id.main_bottom_nav_feed -> changeFragment(FeedFragment())
                R.id.main_bottom_nav_group -> changeFragment(GroupFragment())
                R.id.main_bottom_nav_settings -> changeFragment(SettingsFragment())
            }
            true
        }
        binding.mainBottomNav.selectedItemId = R.id.main_bottom_nav_feed
    }

    private fun changeFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_layout_container, fragment).commit()
    }
}