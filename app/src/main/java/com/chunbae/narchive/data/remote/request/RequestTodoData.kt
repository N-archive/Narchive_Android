package com.chunbae.narchive.data.remote.request

data class RequestTodoData (
    var todoGroupIdx : Int,
    var todoTitle : String,
    var startDate : String,
    var startTime : String,
    var endDate : String,
    var endTime : String
)