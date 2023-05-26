package com.chunbae.narchive.data.remote.request

data class RequestDiaryData(
    val diaryContent: String,
    val locationName: String?,
    val locationAddr: String?,
    val locationX: Double?,
    val locationY: Double?,
    val imagePath : MutableList<String>?,
    val isSimple : String
)
