package com.chunbae.narchive.presentation.ui.search.location.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.domain.repository.KakaoBookSearchRepository
import com.chunbae.narchive.domain.repository.KakaoLocationSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor(private val repo: KakaoLocationSearchRepository) :
    ViewModel() {

    var userInputKeyword = MutableLiveData<String>().apply { value = "" }

    private val _locationSearchData = MutableLiveData<MutableList<LocationData>>()
    val locationSearchData : LiveData<MutableList<LocationData>> = _locationSearchData

    fun searchUserKeywordLocation() {
        viewModelScope.launch {
            userInputKeyword.value?.let { repo.getLocationSearch(it.trim()) }!!
                .onSuccess { _locationSearchData.value = it as MutableList}
        }
    }

}