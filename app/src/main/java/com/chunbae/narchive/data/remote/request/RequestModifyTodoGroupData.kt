package com.chunbae.narchive.data.remote.request

data class RequestModifyTodoGroupData (
    val todoGroupPK : Int,
    val groupTitle : String,
    val groupColor : String
)