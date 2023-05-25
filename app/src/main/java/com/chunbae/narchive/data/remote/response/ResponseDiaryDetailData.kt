package com.chunbae.narchive.data.remote.response

import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.presentation.util.BaseResponse

data class ResponseDiaryDetailData (
    val result : DiaryData
) : BaseResponse()