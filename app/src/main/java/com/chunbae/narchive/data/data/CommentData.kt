package com.chunbae.narchive.data.data

data class CommentData(
    val user : UserData,
    val uploadTime : String,
    val content : String,
    val commentList : List<Comment>
)
