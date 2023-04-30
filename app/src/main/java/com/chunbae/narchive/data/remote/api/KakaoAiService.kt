package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestAiDiaryData
import com.chunbae.narchive.data.remote.response.ResponseAiData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface KakaoAiService {

    @POST("/v1/inference/kogpt/generation")
    suspend fun getAIDiaryData(
        @Header("Authorization") authorization : String,
        @Body requestAiDiaryData : RequestAiDiaryData
    ) : Response<ResponseAiData>

}