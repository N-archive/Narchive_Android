package com.chunbae.narchive.presentation.ui.search.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchMovieViewModel : ViewModel(){

    var userKeyword = MutableLiveData<String>().apply { value = "" }
}