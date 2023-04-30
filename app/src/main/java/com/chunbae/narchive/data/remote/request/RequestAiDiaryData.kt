package com.chunbae.narchive.data.remote.request

data class RequestAiDiaryData(
    val prompt : String,
    val max_tokens : Int = 300,
    val temperature : Double = 0.5,
    val top_p : Double = 0.5
)
