package com.chunbae.narchive.presentation.ui.search.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.domain.repository.KakaoBookSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(private val searchBookRepo : KakaoBookSearchRepository) : ViewModel() {
    private val _searchedBookData = MutableLiveData<MutableList<BookData>>()
    val searchedBookData : LiveData<MutableList<BookData>> = _searchedBookData


    fun getSearchedBookData(userKeyword : String) {
        viewModelScope.launch {
            searchBookRepo.getBookSearchData(userKeyword)
                .onSuccess { _searchedBookData.value = it as MutableList}

        }
    }
}