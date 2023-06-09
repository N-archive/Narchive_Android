package com.chunbae.narchive.presentation.ui.todogroup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.remote.request.RequestTodoGroupData
import com.chunbae.narchive.domain.repository.TodoGroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TodoGroupManageViewModel @Inject constructor(private val todoGroupRepository: TodoGroupRepository) : ViewModel(){

    init {
        getTodoGroupData()
    }

    private val _todoGroupData = MutableLiveData<MutableList<GroupData>>()
    val todoGroupData : LiveData<MutableList<GroupData>> = _todoGroupData

    var newGroupTitle = MutableLiveData<String>().apply { value = "" }

    private val _newGroupColor = MutableLiveData<String?>()
    val newGroupColor : LiveData<String?> = _newGroupColor

    private fun getTodoGroupData() {
        viewModelScope.launch {
            todoGroupRepository.getTodoGroupListData()
                .onSuccess { _todoGroupData.value = it as MutableList }
        }
    }

    fun deleteTodoGroup(todoGroupPK : Int, position : Int) {
        viewModelScope.launch {
            todoGroupRepository.patchTodoGroupData(todoGroupPK)
                .onSuccess { _todoGroupData.value!!.removeAt(position) }
        }
    }

    fun setNewAddColor(color : String) {
        _newGroupColor.value = color
    }

    fun addNewGroup(tag : String) {
        if(newGroupTitle.value != null && _newGroupColor.value != null) {
            viewModelScope.launch {
                if (tag == "ADD") {
                    todoGroupRepository.postTodoGroupData(RequestTodoGroupData(newGroupTitle.value!!, newGroupColor.value!!))
                        .onSuccess { _todoGroupData.value!!.add(_todoGroupData.value!!.size, it) }
                }
            }
        }
    }

    fun initAddNew() {
        newGroupTitle.value = null
        _newGroupColor.value = null
    }
}