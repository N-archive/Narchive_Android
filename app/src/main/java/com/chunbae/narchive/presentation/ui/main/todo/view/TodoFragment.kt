package com.chunbae.narchive.presentation.ui.main.todo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.databinding.FragmentTodoBinding
import com.chunbae.narchive.presentation.ui.main.MainViewModel
import com.chunbae.narchive.presentation.ui.main.todo.adapter.TodoListAdapter
import com.google.android.material.snackbar.Snackbar

class TodoFragment : Fragment() {
    private lateinit var binding : FragmentTodoBinding
    private val viewModel : MainViewModel by activityViewModels()
    private val todoListAdapter by lazy {
        TodoListAdapter(::onTodoChecked, ::onTodoLongClicked)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)

        initBinding()
        initRV()
        observe()

        return binding.root
    }

    private fun initBinding() {
        binding.fragment = this
        binding.isTargetDate = viewModel.isCalClicked.value
        binding.todoData = todoData()
    }

    private fun initRV() {
        binding.fgTodoRvTodoList.adapter = todoListAdapter
        //binding.fgTodoRvTodoList.itemAnimator = null
    }

    private fun observe() {
        viewModel.todoList.observe(requireActivity()) {
            Log.d("----", "observe: ${it.size}")
            todoListAdapter.todoList = it
            todoListAdapter.notifyDataSetChanged()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setCalClickedFalse()
    }

    private fun onTodoChecked(position : Int) {

    }

    private fun onTodoLongClicked(position : Int) {
        viewModel.removeTodo(position)
    }

    fun openAddTodo() {
        requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_layout_container, WriteTodoFragment()).commit()
    }

    /** Dummy */
    fun todoData() : TodoData = TodoData("05월 01일", todoList())

    fun todoList() : List<TodoData.TodoList> = listOf(TodoData.TodoList("13:00", "14:00", "일기 쓰기", "기본", "RED", false))
}