package com.chunbae.narchive.data.remote.response

import com.chunbae.narchive.presentation.util.BaseResponse

data class ResponseBookGroupData (
    val result : List<ResponseBookGroupDataResult>
) : BaseResponse() {
    data class ResponseBookGroupDataResult (
        val bookIdx : Int,
        val bookThumbnailPath : String,
        val bookTitle : String
            )
}