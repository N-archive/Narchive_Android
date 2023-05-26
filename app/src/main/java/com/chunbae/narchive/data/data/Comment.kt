package com.chunbae.narchive.data.data

data class Comment (
    val commentIdx : Int,
    val profilePath : String,
    val nickName : String,
    val content : String,
    val updatedAt : String
        )