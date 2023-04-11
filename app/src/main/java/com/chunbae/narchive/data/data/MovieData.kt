package com.chunbae.narchive.data.data

data class MovieData (
    val movieId : Int,
    val thumbnail : Int?, //:TODO 추후 String으로 변경하기
    val userImage : Int?, //:TODO 추후 String으로 변경하기
    val title : String,
    val director : String,
    val actors : String,
    val releaseAt : String,
    val rating : Float?,
    val keywords : List<String>?,
    val review : String?
)