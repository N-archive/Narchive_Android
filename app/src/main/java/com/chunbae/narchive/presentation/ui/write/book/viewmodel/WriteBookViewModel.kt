package com.chunbae.narchive.presentation.ui.write.book.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.data.KeywordData
import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.domain.repository.BookRepository
import com.chunbae.narchive.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.apache.commons.lang3.mutable.Mutable
import javax.inject.Inject

@HiltViewModel
class WriteBookViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository, private val bookRepository: BookRepository): ViewModel() {

    private val _selectedBook = MutableLiveData<BookData>()
    val selectedBook : LiveData<BookData> = _selectedBook

    private val _keywordItems = MutableLiveData<MutableList<KeywordData>>()
    val keywordItems: LiveData<MutableList<KeywordData>> = _keywordItems

    private val _isGalleryCameraDialogOpened = MutableLiveData<Boolean>(false)
    val isGalleryCameraDialogOpened: LiveData<Boolean> = _isGalleryCameraDialogOpened

    private val _imagePath = MutableLiveData<String>()
    val imagePath : LiveData<String> = _imagePath

    // 0 : 카메라 / 1 : 갤러리
    private var _isGalleryOrCamera = MutableLiveData<Int?>()
    val isGalleryOrCamera : LiveData<Int?> = _isGalleryOrCamera

    private val _userSelectedImage = MutableLiveData<Any>()
    val userSelectedImage : LiveData<Any> = _userSelectedImage

    private val _userState = MutableLiveData<String>()
    val userState : LiveData<String> = _userState

    fun setBookData(book : BookData) {
        _selectedBook.value = book
    }
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

    fun postBookReview(keywords : MutableList<String>, userReview : String?, userRating : Float) {
        if(userSelectedImage.value == null) {
            _userState.value = "사진을 등록해주세요."
            return
        }
        else if(keywords.size in 1..3 && userReview == null) {
            _userState.value = "키워드 혹은 감상평을 제대로 입력해주세요."
        }
        postImageToFirebase(type = if (userSelectedImage.value!!.javaClass.toString().contains("Bitmap")) { "Bitmap" } else { "Uri" })

        viewModelScope.launch {
            postBookReviewToServer(keywords.joinToString(","), userReview!!, userRating)
        }
    }

    private fun postImageToFirebase(type : String) {
        viewModelScope.launch {
            if (type == "Bitmap") {
                _imagePath.value =
                    firebaseRepository.uploadBookOrMovieImageBitmapToFirebase("Book", userSelectedImage.value as Bitmap)
                        .toString()
            } else {
                _imagePath.value =
                    firebaseRepository.uploadBookOrMovieImageUriToFirebase("Book", userSelectedImage.value as Uri).toString()
            }
        }
    }

    fun postBookReviewToServer(keywords : String, userReview : String, userRating : Float) {
        val postBookData = MapBookData(keywords, userReview, userRating)
        viewModelScope.launch {
            bookRepository.postBookReviewData(postBookData)
                .onSuccess { _userState.value = "성공적으로 저장되었습니다." }
        }
    }
    private fun MapBookData(keywords : String, userReview : String, userRating : Float) : RequestBookReviewData {
        return RequestBookReviewData(
            selectedBook.value!!.title,
            selectedBook.value!!.thumbnail,
            selectedBook.value!!.author,
            selectedBook.value!!.publishedAt,
            selectedBook.value!!.publisher,
            userReview,
            userRating,
            keywords,
            _imagePath.value.toString()
        )
    }



    /** Dummy */
    private fun reviewKeywordData(): MutableList<KeywordData> = mutableListOf<KeywordData>().apply {
        add(KeywordData("책에 대한 총평을 선택해주세요.", keywordItems()))
        add(KeywordData("책의 어느 부분이 제일 좋았나요?", keywordItems2()))
        add(KeywordData("이 책을 추천하고 싶으신가요?", keywordItems3()))
        add(KeywordData("이 책을 다시 볼 의향이 있으신가요?", keywordItems4()))
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
            add(KeywordData.Keywords("소재가 신선함", false))
            add(KeywordData.Keywords("스토리가 좋음", false))
            add(KeywordData.Keywords("몰입감이 좋음", false))
            add(KeywordData.Keywords("별로 좋지 않았음", false))
        }

    private fun keywordItems3() : MutableList<KeywordData.Keywords> =
        mutableListOf<KeywordData.Keywords>().apply {
            add(KeywordData.Keywords("추천", false))
            add(KeywordData.Keywords("비추천", false))
        }

    private fun keywordItems4() : MutableList<KeywordData.Keywords> =
        mutableListOf<KeywordData.Keywords>().apply {
            add(KeywordData.Keywords("있음", false))
            add(KeywordData.Keywords("없음", false))
        }
}