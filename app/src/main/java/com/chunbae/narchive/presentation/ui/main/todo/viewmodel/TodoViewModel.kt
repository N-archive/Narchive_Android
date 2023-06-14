package com.chunbae.narchive.presentation.ui.main.todo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.domain.repository.TodoGroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepo : TodoGroupRepository): ViewModel() {

    private val _todoList = MutableLiveData<MutableList<TodoData.TodoList>>()
    val todoList : LiveData<MutableList<TodoData.TodoList>> = _todoList

    init {
        getTodo()
    }

    private fun getTodo() {
        viewModelScope.launch {
            todoRepo.getTodoListData("")
                .onSuccess {
                    _todoList.value = it as MutableList
                }
        }
    }
    fun removeTodo(position : Int) {
        _todoList.value?.remove(_todoList.value!![position])
    }
}