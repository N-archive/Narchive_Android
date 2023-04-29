package com.chunbae.narchive.presentation.ui.write.diary.normal.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunbae.narchive.data.data.LocationData

class WriteNormalDiaryViewModel : ViewModel() {

    private val _selectedLocation = MutableLiveData<LocationData>()
    val selectedLocation : LiveData<LocationData> = _selectedLocation

    private val _selectedImages = MutableLiveData<MutableList<String>>()
    val selectedImages : LiveData<MutableList<String>> = _selectedImages

    fun setLocation(data : LocationData) {
        _selectedLocation.value = data
    }

    fun setImages(data : MutableList<String>) {
        _selectedImages.value = data
    }
}