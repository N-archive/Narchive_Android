package com.chunbae.narchive.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.data.remote.response.ResponseBookGroupData
import com.chunbae.narchive.domain.repository.BookRepository
import com.chunbae.narchive.domain.usecase.DiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val diaryUseCase: DiaryUseCase, private val bookRepository: BookRepository): ViewModel() {

    private val _isBookOrMovie = MutableLiveData<String>().apply { value = "Book" } //Book / Movie
    val isBookOrMovie: LiveData<String> = _isBookOrMovie

    private val _isWriteDialogOpened = MutableLiveData<Boolean>().apply { value = false }
    val isWriteDialogOpened : LiveData<Boolean> = _isWriteDialogOpened

    private val _writeType = MutableLiveData<Int>()
    val writeType : LiveData<Int> = _writeType

    private val _isCalClicked = MutableLiveData<Boolean>(false)
    val isCalClicked : LiveData<Boolean> = _isCalClicked

    private val _feedDiaryData = MutableLiveData<MutableList<FeedData>>()
    val feedDiaryData : LiveData<MutableList<FeedData>> = _feedDiaryData

    private val _bookGroupData = MutableLiveData<MutableList<ResponseBookGroupData.ResponseBookGroupDataResult>>()
    val bookGroupData : LiveData<MutableList<ResponseBookGroupData.ResponseBookGroupDataResult>> = _bookGroupData

    fun setChangeBookOrMovie() {
        _isBookOrMovie.value = if (_isBookOrMovie.value == "Book") "Movie" else "Book"
    }

    fun changeDialogOpenedState() {
        _isWriteDialogOpened.value = _isWriteDialogOpened.value?.not()
    }

    fun setWriteType(position : Int) {
        _writeType.value = position
    }

    fun initWriteType() {
        _writeType.value = 100 //절대 나올 수 없는 값으로 초기화를 대신했다.
    }

    fun setCalClickedFalse() {
        _isCalClicked.value = false
    }

    fun setCalClickedTrue() {
        _isCalClicked.value = true
    }

    fun getFeedData(page : Int) {
        viewModelScope.launch {
            diaryUseCase.getFeedMapping(page)
                .onSuccess { _feedDiaryData.value = it }
        }
    }

    fun getBookGroupData() {
        viewModelScope.launch {
            bookRepository.getBookGroupData()
                .onSuccess { _bookGroupData.value = it as MutableList }
        }
    }

}