package com.chunbae.narchive.data.remote.request

data class RequestNormalDiaryData(
    val diaryContent: String,
    val locationName: String?,
    val locationAddr: String?,
    val locationX: Double?,
    val locationY: Double?
)
