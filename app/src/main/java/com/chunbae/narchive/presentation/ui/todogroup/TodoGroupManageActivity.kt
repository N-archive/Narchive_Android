package com.chunbae.narchive.presentation.ui.todogroup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.databinding.ActivityManageTodoGroupBinding
import com.chunbae.narchive.presentation.ui.main.dialog.AddTodoGroupDialog
import com.chunbae.narchive.presentation.ui.main.dialog.WriteTodoGroupDialog
import com.chunbae.narchive.presentation.ui.todogroup.adapter.TodoGroupAdapter
import com.chunbae.narchive.presentation.ui.todogroup.adapter.util.ItemTouchHelperCallback
import com.chunbae.narchive.presentation.ui.todogroup.adapter.util.ItemTouchHelperListener
import com.chunbae.narchive.presentation.ui.todogroup.viewmodel.TodoGroupManageViewModel
import com.chunbae.narchive.presentation.util.ConvertUtil
import com.jcorp.rc_challenge_4.SwipeHelperCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoGroupManageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityManageTodoGroupBinding
    private val viewModel : TodoGroupManageViewModel by viewModels()
    private val todoGroupAdapter by lazy {
        TodoGroupAdapter(::deleteTodoGroup)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_todo_group)

        initBinding()
        setSwipeRV()
        todoObserve()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.activity = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTodoGroupData()
    }

    private fun setSwipeRV() {
        binding.manageTodoGroupRvGroups.adapter = todoGroupAdapter
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

    private fun todoObserve() {
        viewModel.todoGroupData.observe(this) {
            todoGroupAdapter.groupList = it
            todoGroupAdapter.notifyItemRangeChanged(0, it.size)
        }
    }

    private fun deleteTodoGroup(todoGroupIdx : Int) {
        viewModel.deleteTodoGroup(todoGroupIdx)
    }

    fun openCreateOrEditDialog(tag : String) {
        AddTodoGroupDialog().show(supportFragmentManager, tag)
    }

    /*TEST */
    var test = listOf<GroupData>(GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"),GroupData(0, "1", "RED", "Y"))
}