package com.chunbae.narchive.presentation.ui.write.diary.simple.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunbae.narchive.data.data.LocationData

class WriteSimpleDiaryViewModel : ViewModel() {

    private val _selectedLocation = MutableLiveData<LocationData>()
    val selectedLocation : LiveData<LocationData> = _selectedLocation

    private val _selectedImages = MutableLiveData<MutableList<String>>()
    val selectedImages : LiveData<MutableList<String>> = _selectedImages

    var userInputContent = MutableLiveData<String>().apply { value = "" }

    private val _userInputKeywords = MutableLiveData<MutableList<String>>()
    val userInputKeywords : LiveData<MutableList<String>> = _userInputKeywords


    fun setLocation(data : LocationData) {
        _selectedLocation.value = data
    }

    fun setImages(data : MutableList<String>) {
        _selectedImages.value = data
    }

    fun convertKeywordsToList() {
        _userInputKeywords.value = userInputContent.value?.split(",") as MutableList
        Log.d("----", "convertKeywordsToList: ${userInputKeywords.value?.size}")
    }
}