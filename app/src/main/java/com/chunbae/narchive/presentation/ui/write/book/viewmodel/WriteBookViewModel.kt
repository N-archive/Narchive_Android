package com.chunbae.narchive.presentation.ui.write.book.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.KeywordData

class WriteBookViewModel : ViewModel() {

    private val _keywordItems = MutableLiveData<MutableList<KeywordData>>()
    val keywordItems: LiveData<MutableList<KeywordData>> = _keywordItems

    private val _isGalleryCameraDialogOpened = MutableLiveData<Boolean>(false)
    val isGalleryCameraDialogOpened: LiveData<Boolean> = _isGalleryCameraDialogOpened

    // 0 : 카메라 / 1 : 갤러리
    private var _isGalleryOrCamera = MutableLiveData<Int?>()
    val isGalleryOrCamera : LiveData<Int?> = _isGalleryOrCamera

    private val _userSelectedImage = MutableLiveData<Any>()
    val userSelectedImage : LiveData<Any> = _userSelectedImage

    var userBookRating = MutableLiveData<Float>().apply { value = 0F }

    fun setItem() {
        _keywordItems.value = reviewKeywordData()
    }

    fun setDialogStateChange() {
        _isGalleryCameraDialogOpened.value = _isGalleryCameraDialogOpened.value?.not()
    }

    fun setGalleryOrCamera(position : Int) {
        _isGalleryOrCamera.value = position
    }

    fun initGalleryOrCamera() {
        _isGalleryOrCamera.value = null
    }

    fun setUserSelectedImage(path : Any) {
        _userSelectedImage.value = path
    }

    /** Dummy */
    private fun reviewKeywordData(): MutableList<KeywordData> = mutableListOf<KeywordData>().apply {
        add(KeywordData("1 번째 키워드 아이템입니다.", keywordItems(), null, true))
        add(KeywordData("2 번째 키워드 아이템입니다.", keywordItems2(), null, true))
        add(KeywordData("3 번째 키워드 아이템입니다.", keywordItems(), null, true))
        add(KeywordData("4 번째 키워드 아이템입니다.", keywordItems2(), null, true))
        add(KeywordData("5 번째 키워드 아이템입니다.", keywordItems(), null, true))
    }

    private fun keywordItems(): MutableList<KeywordData.Keywords> =
        mutableListOf<KeywordData.Keywords>().apply {
            add(KeywordData.Keywords("매우 만족", false))
            add(KeywordData.Keywords("만족", false))
            add(KeywordData.Keywords("조금 만족", false))
            add(KeywordData.Keywords("보통", false))
            add(KeywordData.Keywords("조금 불만족", false))
            add(KeywordData.Keywords("불만족", false))
            add(KeywordData.Keywords("매우 불만족", false))
        }

    private fun keywordItems2(): MutableList<KeywordData.Keywords> =
        mutableListOf<KeywordData.Keywords>().apply {
            add(KeywordData.Keywords("1번", false))
            add(KeywordData.Keywords("2번", false))
            add(KeywordData.Keywords("3번", false))
            add(KeywordData.Keywords("4번", false))
            add(KeywordData.Keywords("5번", false))
            add(KeywordData.Keywords("6번", false))
            add(KeywordData.Keywords("7번", false))
        }
}