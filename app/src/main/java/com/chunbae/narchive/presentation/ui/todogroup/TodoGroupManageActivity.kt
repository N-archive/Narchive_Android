package com.chunbae.narchive.presentation.ui.todogroup

import android.os.Bundle
import android.util.Log
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
        TodoGroupAdapter(::deleteTodoGroup, ::openCreateOrEditDialog, ::setEditGroup, ::changeDefaultGroup)
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

    private fun setSwipeRV() {
        binding.manageTodoGroupRvGroups.adapter = todoGroupAdapter
        val itemTouchHelper = ItemTouchHelperCallback(todoGroupAdapter)
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.manageTodoGroupRvGroups)
    }

    private fun todoObserve() {
        viewModel.todoGroupData.observe(this) {
            todoGroupAdapter.groupList = it
            todoGroupAdapter.notifyDataSetChanged()
        }
    }

    private fun setEditGroup(todoGroup : GroupData) {
        viewModel.setEditGroup(todoGroup)
    }

    private fun deleteTodoGroup(todoGroupIdx : Int) {
        viewModel.deleteTodoGroup(todoGroupIdx)
    }

    private fun changeDefaultGroup(pastIdx : Int, curIdx : Int) {
        viewModel.changeDefaultGroup(pastIdx, curIdx)
    }

    fun openCreateOrEditDialog(tag : String) {
        AddTodoGroupDialog().show(supportFragmentManager, tag)
    }
}