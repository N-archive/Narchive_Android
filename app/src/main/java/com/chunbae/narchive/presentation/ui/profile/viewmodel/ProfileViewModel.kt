package com.chunbae.narchive.presentation.ui.profile.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.data.remote.request.RequestProfileData
import com.chunbae.narchive.domain.repository.FirebaseRepository
import com.chunbae.narchive.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseRepo: FirebaseRepository,
    private val profileRepo: ProfileRepository
) : ViewModel() {

    private val _isGalleryCameraDialogOpened = MutableLiveData<Boolean>(false)
    val isGalleryCameraDialogOpened: LiveData<Boolean> = _isGalleryCameraDialogOpened

    // 0 : 카메라 / 1 : 갤러리
    private var _isGalleryOrCamera = MutableLiveData<Int?>()
    val isGalleryOrCamera: LiveData<Int?> = _isGalleryOrCamera

    private val _userSelectedImage = MutableLiveData<Any>()
    val userSelectedImage: LiveData<Any> = _userSelectedImage

    var userNickName = MutableLiveData<String>().apply { value = "" }

    private var _profileType: String = ""

    private val _isProfileFinished = MutableLiveData<Boolean>(false)
    val isProfileFinished: LiveData<Boolean> = _isProfileFinished

    private var _profileURL = MutableLiveData<String>()
    val profileURL: LiveData<String> = _profileURL

    private var _uploadResultCode = MutableLiveData<Int>()
    val uploadResultCode : LiveData<Int> = _uploadResultCode

    fun setDialogStateChange() {
        _isGalleryCameraDialogOpened.value = _isGalleryCameraDialogOpened.value?.not()
    }

    fun setGalleryOrCamera(position: Int) {
        _isGalleryOrCamera.value = position
    }

    fun initGalleryOrCamera() {
        _isGalleryOrCamera.value = null
    }

    fun setUserSelectedImage(path: Any) {
        _userSelectedImage.value = path

        _profileType = if (path.javaClass.toString().contains("Bitmap")) {
            "Bitmap"
        } else {
            "Uri"
        }
    }

    fun setProfileFinished() {
        _isProfileFinished.value =
            userSelectedImage.value.toString().isNotEmpty() && !userNickName.value.isNullOrEmpty()
    }

    fun postImageToFirebase() {
        viewModelScope.launch {
            if (_profileType == "Bitmap") {
                _profileURL.value =
                    firebaseRepo.uploadProfileBitmapToFirebase(userSelectedImage.value as Bitmap)
                        .toString()
            } else {
                _profileURL.value =
                    firebaseRepo.uploadProfileToFirebase(userSelectedImage.value as Uri).toString()
            }
        }
    }

    fun uploadToServer() {
        viewModelScope.launch {
            profileRepo.uploadProfileData(RequestProfileData(userNickName.value!!, profileURL.value!!))
                .onSuccess {
                    _uploadResultCode.value = it.code
                }
        }
    }
}