package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestDiaryData
import com.chunbae.narchive.data.remote.response.ResponseDiaryDetailData
import com.chunbae.narchive.data.remote.response.ResponseNormalDiary
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DiaryService {

    @POST("diaries")
    suspend fun postNormalDiary(
        @Body req : RequestDiaryData
    ) : Response<ResponseNormalDiary>

    @GET("diaries/{diaryIdx}")
    suspend fun getDiaryDetail(
        @Path("diaryIdx") diaryIdx : Int
    ) : Response<ResponseDiaryDetailData>
}