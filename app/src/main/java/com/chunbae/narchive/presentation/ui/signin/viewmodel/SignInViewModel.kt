package com.chunbae.narchive.presentation.ui.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val _userKakaoId = MutableLiveData<String>()
    val userKakaoId : LiveData<String> = _userKakaoId

    fun setUserKakaoId(id : String) {
        _userKakaoId.value = id
    }

}