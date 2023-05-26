package com.chunbae.narchive.data.remote.response

import com.chunbae.narchive.data.data.CommentData
import com.chunbae.narchive.presentation.util.BaseResponse

data class ResponseDiaryCommentData (
    val result : CommentData
) : BaseResponse()