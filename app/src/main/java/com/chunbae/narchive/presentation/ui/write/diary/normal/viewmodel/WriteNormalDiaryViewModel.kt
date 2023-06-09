package com.chunbae.narchive.presentation.ui.write.diary.normal.viewmodel

import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.domain.repository.FirebaseRepository
import com.chunbae.narchive.domain.repository.KakaoAiDiaryRepository
import com.chunbae.narchive.domain.usecase.DiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteNormalDiaryViewModel @Inject constructor(
    private val aiRepo: KakaoAiDiaryRepository,
    private val diaryUseCase: DiaryUseCase,
    private val firebaseRepo: FirebaseRepository
) : ViewModel() {

    private val _selectedLocation = MutableLiveData<LocationData>()
    val selectedLocation: LiveData<LocationData> = _selectedLocation

    private val _selectedImages = MutableLiveData<MutableList<String>>()
    val selectedImages: LiveData<MutableList<String>> = _selectedImages

    private val imageDownloadPath = mutableListOf<String>()

    var userInputContent = MutableLiveData<String>().apply { value = "" }

    var aiGeneratedContent = MutableLiveData<String?>()

    var isDialogOpened = MutableLiveData<Boolean>(false)

    private val _diaryState = MutableLiveData<String>()
    val diaryState: LiveData<String> = _diaryState

    fun setLocation(data: LocationData) {
        _selectedLocation.value = data
    }

    fun setImages(data: MutableList<String>) {
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

    fun uploadImageToFirebase() {
        viewModelScope.async {
            for (i in _selectedImages.value!!) {
                imageDownloadPath.add(firebaseRepo.uploadProfileToFirebase(i.toUri()).toString())
            }
        }
        postSimpleDiary()
    }

    fun postSimpleDiary() {
        viewModelScope.launch {
            userInputContent.value?.let {
                diaryUseCase.postMapping(
                    it,
                    selectedLocation.value,
                    imageDownloadPath,
                    "F"
                )
            }
                ?.onSuccess { _diaryState.value = it }
                ?.onFailure { _diaryState.value = "등록에 실패했습니다." }
        }
    }
}