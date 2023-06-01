package com.chunbae.narchive.presentation.ui.search.movie.viewmodel

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.remote.response.ResponseMovieData
import com.chunbae.narchive.domain.repository.MovieSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(private val movieRepo : MovieSearchRepository): ViewModel(){

    private val _searchedMovieList = MutableLiveData<MutableList<ResponseMovieData.ResultSearchMovie>>()
    val searchedMovieList : LiveData<MutableList<ResponseMovieData.ResultSearchMovie>> = _searchedMovieList
    fun getSearchedMovieData(userKeyword : String) {
        viewModelScope.launch {
            movieRepo.getMovieLists(userKeyword)
                .onSuccess { _searchedMovieList.value = it as MutableList}

        }
    }
}