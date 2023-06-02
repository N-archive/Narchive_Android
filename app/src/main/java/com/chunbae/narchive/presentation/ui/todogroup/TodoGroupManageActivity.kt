package com.chunbae.narchive.presentation.ui.todogroup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.databinding.ActivityManageTodoGroupBinding
import com.chunbae.narchive.presentation.ui.todogroup.adapter.TodoGroupAdapter
import com.chunbae.narchive.presentation.ui.todogroup.adapter.util.ItemTouchHelperCallback
import com.chunbae.narchive.presentation.ui.todogroup.adapter.util.ItemTouchHelperListener
import com.chunbae.narchive.presentation.util.ConvertUtil
import com.jcorp.rc_challenge_4.SwipeHelperCallback

class TodoGroupManageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityManageTodoGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_todo_group)

        binding.manageTodoGroupRvGroups.adapter = TodoGroupAdapter().also { it.groupList = test as MutableList }
        val swipeHelperCallback = SwipeHelperCallback().apply {
            setClamp(ConvertUtil.dpToPx(this@TodoGroupManageActivity, 100F))
        }
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.manageTodoGroupRvGroups)
        binding.manageTodoGroupRvGroups.apply {
            setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(this)
                false
            }
        }
    }

    /*TEST */
    var test = listOf<GroupData>(GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"))
}