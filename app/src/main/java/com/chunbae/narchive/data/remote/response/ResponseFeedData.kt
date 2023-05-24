package com.chunbae.narchive.data.remote.response

import com.chunbae.narchive.presentation.util.BaseResponse

data class ResponseFeedData (
    val result : List<ResponseFeedResult>
) : BaseResponse() {
    data class ResponseFeedResult(
        val feedIdx : Int,
        val nickName : String,
        val profilePath : String,
        val updatedAt : String,
        val isSimple : String,
        val content : String,
        val thumbnailPath : String,
        val imageCount : Int,
        val locationName : String,
        val commentCount : Int
    )
}