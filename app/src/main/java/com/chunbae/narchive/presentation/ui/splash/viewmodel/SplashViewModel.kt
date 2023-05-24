package com.chunbae.narchive.presentation.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepo : AuthRepository): ViewModel() {
    private val _autoSignInResult = MutableLiveData<String>()
    val autoSignInResult : LiveData<String> = _autoSignInResult

    init {
        getAutoSignIn()
    }

    private fun getAutoSignIn() {
        viewModelScope.launch {
            authRepo.getAutoSignIn()
                .onSuccess {
                    _autoSignInResult.value = it.isNew
                    authRepo.saveUserInfoInLocal(it)
                }
        }
    }
}