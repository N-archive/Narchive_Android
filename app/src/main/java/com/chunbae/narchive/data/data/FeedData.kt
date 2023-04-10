package com.chunbae.narchive.data.data

data class FeedData(
    val user : UserData,
    val uploadTime : String,
    val content : String?, /** 일반 일기 작성시 사용됨 */
    val thumbNail : Int, //TODO: 추후 서버 연결시 String으로 변경할 것
    val imageCount : Int,
    val locationAddress : String,
    val commentCount : String,
    val keywords : List<String>?, /** 간단 일기 작성시 사용됨 */
    val isSimple : String /** T / F */
)
