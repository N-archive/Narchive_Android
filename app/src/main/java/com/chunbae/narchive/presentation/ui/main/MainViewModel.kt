package com.chunbae.narchive.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isBookOrMovie = MutableLiveData<String>().apply { "Book" } //Book / Movie
    val isBookOrMovie: LiveData<String> = _isBookOrMovie


    fun setChangeBookOrMovie() {
        _isBookOrMovie.value = if (_isBookOrMovie.value == "Book") "Movie" else "Book"
    }
}