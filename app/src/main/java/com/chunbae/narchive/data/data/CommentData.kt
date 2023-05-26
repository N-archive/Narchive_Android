package com.chunbae.narchive.data.data

data class CommentData(
    val profilePath : String,
    val nickName : String,
    val updatedAt : String,
    val content : String,
    val commentList : MutableList<Comment>?
)
