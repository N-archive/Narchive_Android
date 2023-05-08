package com.chunbae.narchive.data.remote.response

data class ResponseSignInData (
    val userIdx : Int,
    val jwt : String,
    val isNew : String
)