package com.chunbae.narchive.presentation.ui.write.diary.normal.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.domain.repository.KakaoAiDiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteNormalDiaryViewModel @Inject constructor(private val aiRepo : KakaoAiDiaryRepository): ViewModel() {

    private val _selectedLocation = MutableLiveData<LocationData>()
    val selectedLocation : LiveData<LocationData> = _selectedLocation

    private val _selectedImages = MutableLiveData<MutableList<String>>()
    val selectedImages : LiveData<MutableList<String>> = _selectedImages

    var userInputContent = MutableLiveData<String>().apply { value = "" }

    var aiGeneratedContent = MutableLiveData<String?>()

    var isDialogOpened = MutableLiveData<Boolean>(false)

    fun setLocation(data : LocationData) {
        _selectedLocation.value = data
    }

    fun setImages(data : MutableList<String>) {
        _selectedImages.value = data
    }

    fun getAiDiarySample() {
        viewModelScope.launch {
            userInputContent.value.let { aiRepo.getKakaoAiDiaryData(it ?: "") }
                .onSuccess { aiGeneratedContent.value = it.text }
        }
    }

    fun onAcceptAiGenerated() {
        userInputContent.value += aiGeneratedContent.value
    }
    fun initAiGenerated() {
        aiGeneratedContent.value = null
    }

    fun changeDialogOpenedState() {
        isDialogOpened.value = isDialogOpened.value?.not()
    }
}