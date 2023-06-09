package com.chunbae.narchive.presentation.ui.todogroup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.remote.request.RequestModifyTodoGroupData
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

    val todoGroupData = MutableLiveData<MutableList<GroupData>>()

    var newGroupTitle = MutableLiveData<String>().apply { value = "" }

    private val _newGroupColor = MutableLiveData<String?>()
    val newGroupColor : LiveData<String?> = _newGroupColor

    private val _editGroup = MutableLiveData<GroupData?>()
    val editGroup : LiveData<GroupData?> = _editGroup

    private fun getTodoGroupData() {
        viewModelScope.launch {
            todoGroupRepository.getTodoGroupListData()
                .onSuccess { todoGroupData.value = it as MutableList }
        }
    }

    fun deleteTodoGroup(todoGroupPK : Int) {
        viewModelScope.launch {
            todoGroupRepository.patchTodoGroupData(todoGroupPK)
                .onSuccess { getTodoGroupData() }
        }
    }

    fun setNewAddColor(color : String) {
        _newGroupColor.value = color
    }

    fun addNewGroup(tag : String) {
        Log.d("----", "addNewGroup: ${newGroupTitle.value} / ${newGroupColor.value}")
        if(newGroupTitle.value != null && _newGroupColor.value != null) {
            viewModelScope.launch {
                if (tag == "ADD") {
                    todoGroupRepository.postTodoGroupData(RequestTodoGroupData(newGroupTitle.value!!, newGroupColor.value!!))
                        .onSuccess { getTodoGroupData() }
                }
                else if (tag == "EDIT") {
                    todoGroupRepository.putTodoGroupData(RequestModifyTodoGroupData(editGroup.value!!.todoGroupIdx!!, newGroupTitle.value!!, newGroupColor.value!!))
                        .onSuccess { getTodoGroupData() }
                }
            }
        }
    }

    fun setEditGroup(groupData: GroupData) {
        _editGroup.value = groupData
    }

    fun initAddNew() {
        newGroupTitle.value = null
        _newGroupColor.value = null
        _editGroup.value = null
    }
}