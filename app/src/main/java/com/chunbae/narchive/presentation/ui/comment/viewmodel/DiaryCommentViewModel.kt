package com.chunbae.narchive.presentation.ui.comment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.CommentData
import com.chunbae.narchive.domain.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryCommentViewModel @Inject constructor(private val commentRepo : CommentRepository): ViewModel() {
    private val _commentData = MutableLiveData<CommentData>()
    val commentData : LiveData<CommentData> = _commentData

    fun getCommentData(diaryIdx : Int) {
        viewModelScope.launch {
            commentRepo.getDiaryComments(diaryIdx)
                .onSuccess { _commentData.value = it }
        }
    }


}