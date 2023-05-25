package com.chunbae.narchive.data.data

data class DiaryData (
    val diaryIdx : Int,
    val nickName : String,
    val profilePath : String,
    val updatedAt : String,
    val uploadedAt : String,
    var content : String?,/** 일반 일기 작성시 사용됨 */
    val locationName : String,
    var keywords : MutableList<String>?,/** 간단 일기 작성시 사용됨 */
    val images : MutableList<String>?, //:TODO 추후 서버 연결 후 String 으로 변경
    val isSimple : String /** T / F */
)