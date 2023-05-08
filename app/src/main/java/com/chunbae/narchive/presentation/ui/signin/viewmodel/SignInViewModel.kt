package com.chunbae.narchive.presentation.ui.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.Narchive
import com.chunbae.narchive.data.remote.request.RequestSignInData
import com.chunbae.narchive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repo : AuthRepository) : ViewModel() {
    private val _userKakaoId = MutableLiveData<String>()
    val userKakaoId : LiveData<String> = _userKakaoId

    private val _jwt = MutableLiveData<String>()
    val jwt : LiveData<String> = _jwt

    fun setUserKakaoId(id : String) {
        _userKakaoId.value = id
    }

    fun postUserData() {
        viewModelScope.launch {
            repo.postUserData(RequestSignInData(userKakaoId.value!!))
                .onSuccess {
                    _jwt.value = it.jwt
                    repo.saveUserInfoInLocal(it)
                }
        }
    }

}