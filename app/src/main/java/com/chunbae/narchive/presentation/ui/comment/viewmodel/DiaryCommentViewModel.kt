package com.chunbae.narchive.presentation.ui.comment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.data.CommentData
import com.chunbae.narchive.data.remote.request.RequestCommentData
import com.chunbae.narchive.domain.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryCommentViewModel @Inject constructor(private val commentRepo : CommentRepository): ViewModel() {
    val userInputComment = MutableLiveData<String>().apply { value = "" }

    private val _diaryIdx = MutableLiveData<Int>()
    val diaryIdx : LiveData<Int> = _diaryIdx

    private val _commentData = MutableLiveData<CommentData>()
    val commentData : LiveData<CommentData> = _commentData

    fun setDiaryIdx(diaryIdx: Int) {
        _diaryIdx.value = diaryIdx
    }

    fun getCommentData() {
        viewModelScope.launch {
            diaryIdx.value?.let {
                commentRepo.getDiaryComments(it)
                    .onSuccess { _commentData.value = it }
            }
        }
    }

    fun postCommentData() {
        viewModelScope.launch {
            userInputComment.value?.let { commentRepo.postDiaryComment(diaryIdx.value!!, RequestCommentData(it))
                .onSuccess {
                    getCommentData()
                    userInputComment.value = ""
                }}
        }
    }

}