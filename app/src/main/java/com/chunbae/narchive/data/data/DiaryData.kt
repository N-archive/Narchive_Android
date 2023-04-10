package com.chunbae.narchive.data.data

data class DiaryData (
    val diaryId : Int,
    val user : UserData,
    val uploadTime : String,
    val uploadedAt : String,
    val content : String?, /** 일반 일기 작성시 사용됨 */
    val locationAddress : String,
    val keywords : List<String>?, /** 간단 일기 작성시 사용됨 */
    val imageList : List<Int>?, //:TODO 추후 서버 연결 후 String 으로 변경
    val isSimple : String /** T / F */
)