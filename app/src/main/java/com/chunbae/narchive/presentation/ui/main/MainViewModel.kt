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


    fun setChangeBookOrMovie() {
        _isBookOrMovie.value = if (_isBookOrMovie.value == "Book") "Movie" else "Book"
    }

    fun changeDialogOpenedState() {
        _isWriteDialogOpened.value = _isWriteDialogOpened.value?.not()
    }
}