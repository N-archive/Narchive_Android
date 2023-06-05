package com.chunbae.narchive.presentation.ui.todogroup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.domain.repository.TodoGroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TodoGroupManageViewModel @Inject constructor(private val todoGroupRepository: TodoGroupRepository) : ViewModel(){
    private val _todoGroupData = MutableLiveData<MutableList<GroupData>>()
    val todoGroupData : LiveData<MutableList<GroupData>> = _todoGroupData


    fun getTodoGroupData() {
        viewModelScope.launch {
            todoGroupRepository.getTodoGroupListData()
                .onSuccess { _todoGroupData.value = it as MutableList }
        }
    }
}