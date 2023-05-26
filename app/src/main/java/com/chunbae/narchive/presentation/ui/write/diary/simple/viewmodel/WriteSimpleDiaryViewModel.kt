package com.chunbae.narchive.presentation.ui.write.diary.simple.viewmodel

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.domain.repository.FirebaseRepository
import com.chunbae.narchive.domain.usecase.DiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteSimpleDiaryViewModel @Inject constructor(private val diaryUseCase: DiaryUseCase,
                                                    private val firebaseRepo: FirebaseRepository
): ViewModel() {

    private val _selectedLocation = MutableLiveData<LocationData>()
    val selectedLocation : LiveData<LocationData> = _selectedLocation

    private val _selectedImages = MutableLiveData<MutableList<String>>()
    val selectedImages : LiveData<MutableList<String>> = _selectedImages

    var userInputContent = MutableLiveData<String>().apply { value = "" }

    private val imageDownloadPath = mutableListOf<String>()

    private val _userInputKeywords = MutableLiveData<MutableList<String>>()
    val userInputKeywords : LiveData<MutableList<String>> = _userInputKeywords

    private val _diaryState = MutableLiveData<String>()
    val diaryState: LiveData<String> = _diaryState
    fun setLocation(data : LocationData) {
        _selectedLocation.value = data
    }

    fun setImages(data : MutableList<String>) {
        _selectedImages.value = data
    }

    fun convertKeywordsToList() {
        _userInputKeywords.value = userInputContent.value?.split(",") as MutableList
    }

    fun uploadImageToFirebase() {
        viewModelScope.async {
            for (i in _selectedImages.value!!) {
                imageDownloadPath.add(firebaseRepo.uploadProfileToFirebase(i.toUri()).toString())
            }
        }
        postNormalDiary()
    }

    fun postNormalDiary() {
        viewModelScope.launch {
            userInputContent.value?.let {
                diaryUseCase.postMapping(
                    it,
                    selectedLocation.value,
                    imageDownloadPath,
                    "T"
                )
            }
                ?.onSuccess { _diaryState.value = it }
                ?.onFailure { _diaryState.value = "등록에 실패했습니다." }
        }
    }
}