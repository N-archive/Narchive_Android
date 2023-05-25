package com.chunbae.narchive.presentation.ui.detail.diary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.domain.usecase.DiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailDiaryViewModel @Inject constructor(private val diaryUseCase: DiaryUseCase): ViewModel() {

    private val _diaryDetailData = MutableLiveData<DiaryData>()
    val diaryDetailData : LiveData<DiaryData> = _diaryDetailData

    fun getDiaryDetailData(diaryIdx : Int) {
        viewModelScope.launch {
            diaryUseCase.getDiaryDetailMapping(diaryIdx)
                .onSuccess { _diaryDetailData.value = it }
        }
    }
}