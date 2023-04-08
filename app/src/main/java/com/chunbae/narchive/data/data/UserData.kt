package com.chunbae.narchive.data.data

data class UserData(
    val userIdx : Int,
    val userProfileImage : Int, //TODO: 추후 서버 연결시 String으로 변경할 것
    val userNickName : String
)
