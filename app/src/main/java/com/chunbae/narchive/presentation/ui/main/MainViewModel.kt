package com.chunbae.narchive.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isBookOrMovie = MutableLiveData<String>().apply { value = "Book" } //Book / Movie
    val isBookOrMovie: LiveData<String> = _isBookOrMovie

    private val _isWriteDialogOpened = MutableLiveData<Boolean>().apply { value = false }
    val isWriteDialogOpened : LiveData<Boolean> = _isWriteDialogOpened

    private val _writeType = MutableLiveData<Int>()
    val writeType : LiveData<Int> = _writeType

    private val _isCalClicked = MutableLiveData<Boolean>(false)
    val isCalClicked : LiveData<Boolean> = _isCalClicked


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
}