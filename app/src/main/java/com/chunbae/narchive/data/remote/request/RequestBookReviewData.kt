package com.chunbae.narchive.data.remote.request

data class RequestBookReviewData(
    val bookTitle : String,
    val bookThumbnailPath : String?,
    val bookAuthor : String,
    val bookPublishedAt : String,
    val bookPublisher : String,
    val content : String,
    val rating : Float,
    val keyword : String,
    val reviewImagePath : String
)
