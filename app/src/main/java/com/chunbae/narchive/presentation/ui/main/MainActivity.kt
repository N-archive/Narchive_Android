package com.chunbae.narchive.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivityMainBinding
import com.chunbae.narchive.presentation.ui.main.calendar.CalendarFragment
import com.chunbae.narchive.presentation.ui.main.dialog.ContentWriteDialog
import com.chunbae.narchive.presentation.ui.main.feed.view.FeedFragment
import com.chunbae.narchive.presentation.ui.main.group.view.GroupFragment
import com.chunbae.narchive.presentation.ui.main.settings.SettingsFragment
import com.chunbae.narchive.presentation.ui.main.todo.TodoFragment
import com.chunbae.narchive.presentation.ui.search.book.view.SearchBookActivity
import com.chunbae.narchive.presentation.ui.search.movie.view.SearchMovieActivity
import com.chunbae.narchive.presentation.ui.write.diary.normal.view.WriteNormalDiaryActivity
import com.chunbae.narchive.presentation.ui.write.diary.simple.view.WriteSimpleDiaryActivity

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

        observeBottomSheet()
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

    private fun observeBottomSheet() {
        viewModel.isWriteDialogOpened.observe(this) {
            if(it) ContentWriteDialog().show(supportFragmentManager, "BottomSheet")
        }

        viewModel.writeType.observe(this) {
            if(it != 100) {
                when(it) {
                    0 -> openSimpleDiaryActivity()
                    1 -> openNormalDiaryActivity()
                    else -> openSearchActivity(it)
                }
            }
        }
    }

    private fun openSimpleDiaryActivity() {
        startActivity(Intent(this, WriteSimpleDiaryActivity::class.java))
    }
    private fun openNormalDiaryActivity() {
        startActivity(Intent(this, WriteNormalDiaryActivity::class.java))
    }
    private fun openSearchActivity(type : Int) {
        if(type == 2) startActivity(Intent(this, SearchBookActivity::class.java))
        else startActivity(Intent(this, SearchMovieActivity::class.java))
    }

    private fun changeFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_layout_container, fragment).commit()
    }

}