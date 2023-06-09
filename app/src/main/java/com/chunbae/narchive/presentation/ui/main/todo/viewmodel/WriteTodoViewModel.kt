package com.chunbae.narchive.presentation.ui.main.todo.viewmodel

import android.icu.text.SimpleDateFormat
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
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WriteTodoViewModel @Inject constructor(private val todoGroupRepository: TodoGroupRepository): ViewModel() {

    private val _startDate = MutableLiveData<String>().apply {
        value = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(
            Date(System.currentTimeMillis())
        )
    }
    val startDate: LiveData<String> = _startDate

    private val _startTime = MutableLiveData<String>().apply {
        value = SimpleDateFormat("HH : mm", Locale.KOREA).format(Date(System.currentTimeMillis()))
    }
    val startTime: LiveData<String> = _startTime

    private val _endDate = MutableLiveData<String>().apply { value = startDate.value }
    val endDate: LiveData<String> = _endDate

    private val _endTime = MutableLiveData<String>().apply { value = startTime.value }
    val endTime: LiveData<String> = _endTime

    private val _startHour = MutableLiveData<String>().apply { value = SimpleDateFormat("HH", Locale.KOREA).format(Date(System.currentTimeMillis())) }
    val startHour: LiveData<String> = _startHour

    private val _startMin = MutableLiveData<String>().apply { value = SimpleDateFormat("mm", Locale.KOREA).format(Date(System.currentTimeMillis())) }
    val startMin: LiveData<String> = _startMin

    private val _endHour = MutableLiveData<String>().apply { value = startHour.value }
    val endHour: LiveData<String> = _endHour

    private val _endMin = MutableLiveData<String>().apply { value = endHour.value }
    val endMin: LiveData<String> = _endMin

    private val _isAllday = MutableLiveData<Boolean>().apply { value = false }
    val isAllday: LiveData<Boolean> = _isAllday

    private var _isStartDay = MutableLiveData<Int>()
    val isStartDay: LiveData<Int> = _isStartDay

    private val _isGroupDialogOpened = MutableLiveData<Boolean>().apply { value = false }
    val isGroupDialogOpened: LiveData<Boolean> = _isGroupDialogOpened

    private val _userGroupList = MutableLiveData<MutableList<GroupData>>()
    val userGroupList: LiveData<MutableList<GroupData>> = _userGroupList

    private val _selectedGroup = MutableLiveData<GroupData>().apply { value = GroupData(0, "기본", "PINK", "N") }
    val selectedGroup : LiveData<GroupData> = _selectedGroup



    fun manageCalendarState(state: Int) { // 0:all gone / 1 : start Cal / 2 : end Cal / 3 : start Time / 4 : end Time
        _isStartDay.value = state
    }

    fun getGroupList() {
        viewModelScope.launch {
            todoGroupRepository.getTodoGroupListData()
                .onSuccess { _userGroupList.value = it.apply {
                    (this as MutableList).add(GroupData(0, "일정 그룹 관리하기", "Null", "N"))
                } as MutableList }
        }
    }

    fun setDate(date: LocalDate) {
        if (isStartDay.value == 1) {
            _startDate.value = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(
                Date.from(
                    date.atStartOfDay(ZoneId.systemDefault()).toInstant()
                )
            )
        } else if (isStartDay.value == 2) {
            _endDate.value = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(
                Date.from(
                    date.atStartOfDay(ZoneId.systemDefault()).toInstant()
                )
            )
        }
        _isStartDay.value = 0
    }

    fun setTime(value: String, isHour: Boolean) {
        if (isStartDay.value == 3) {
            if (isHour) _startHour.value = value else _startMin.value = value
            updateTime(true)
        } else if (isStartDay.value == 4) {
            if (isHour) _endHour.value = value else _endMin.value = value
            updateTime(false)
        }
    }

    private fun updateTime(isStart: Boolean) {
        if (isStart) _startTime.value = "${startHour.value} : ${startMin.value}"
        else _endTime.value = "${endHour.value} : ${endMin.value}"
    }

    fun groupDialogOpen() {
        _isGroupDialogOpened.value = _isGroupDialogOpened.value?.not()
    }

    fun setGroupSelect(position : Int) {
        _selectedGroup.value = userGroupList.value!![position]
    }


}
