package com.chunbae.narchive.data.remote.response

import com.chunbae.narchive.presentation.util.BaseResponse

data class ResponseBookDetailData (
    val result : ResponseBookDetailResult
) : BaseResponse() {
    data class ResponseBookDetailResult(
        val bookThumbnailPath : String,
        val bookTitle : String,
        val bookAuthor : String,
        val bookPublishedAt : String,
        val bookPublisher : String,
        val content : String?,
        val keyword : String?,
        val rating : Float,
        val reviewImagePath : String
    )
}