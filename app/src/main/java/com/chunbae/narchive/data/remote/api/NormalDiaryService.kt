package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.presentation.util.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NormalDiaryService {

    @POST("diaries/normal")
    suspend fun postNormalDiary(
        @Body req : RequestNormalDiaryData
    ) : Response<BaseResponse>
}