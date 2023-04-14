package com.chunbae.narchive.data.data

data class BookData (
    val bookId : Int?,
    val thumbnail : String?, //:TODO 추후 String으로 변경하기
    val userImage : Int?, //:TODO 추후 String으로 변경하기
    val title : String,
    val author : String,
    val publisher : String,
    val publishedAt : String,
    val rating : Float?,
    val keywords : List<String>?,
    val review : String?
)