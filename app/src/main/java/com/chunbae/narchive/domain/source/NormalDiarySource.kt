package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.presentation.util.BaseResponse

interface NormalDiarySource {

    suspend fun postNormalDiary(body : RequestNormalDiaryData) : Result<String>
}