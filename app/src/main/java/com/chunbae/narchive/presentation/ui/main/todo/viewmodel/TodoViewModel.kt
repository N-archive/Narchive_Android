package com.chunbae.narchive.presentation.ui.main.todo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunbae.narchive.data.data.TodoData

class TodoViewModel : ViewModel() {

    private val _todoList = MutableLiveData<MutableList<TodoData.TodoList>>()
    val todoList : LiveData<MutableList<TodoData.TodoList>> = _todoList

    init {
        setTodo()
    }

    private fun setTodo() {
        _todoList.value = todoList() as MutableList
    }

    fun removeTodo(position : Int) {
        _todoList.value?.remove(_todoList.value!![position])
        Log.d("----", "removeTodo: ${todoList.value?.size}")
    }

    /** Dummy */
    fun todoData() : TodoData = TodoData("05월 01일", todoList())

    fun todoList() : List<TodoData.TodoList> = mutableListOf<TodoData.TodoList>().apply {
        for (i in 0 until 10){
            add(TodoData.TodoList(i, "13:00", "14:00", "일기 쓰기", "기본", "RED", false))
        }
    }
}