package com.chunbae.narchive.presentation.ui.detail.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.remote.response.ResponseBookDetailData
import com.chunbae.narchive.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(private val bookRepo : BookRepository): ViewModel() {
    private val _selectedBookPK = MutableLiveData<Int>()
    val selectedBookPk : LiveData<Int> = _selectedBookPK

    private val _selectedBookData = MutableLiveData<ResponseBookDetailData.ResponseBookDetailResult>()
    val selectedBookData : LiveData<ResponseBookDetailData.ResponseBookDetailResult> = _selectedBookData

    fun setBookPK(bookPK : Int) {
        _selectedBookPK.value = bookPK
        getSelectedBookData()
    }

    private fun getSelectedBookData() {
        viewModelScope.launch {
            bookRepo.getBookDetailData(selectedBookPk.value!!)
                .onSuccess { _selectedBookData.value = it }
        }
    }
}