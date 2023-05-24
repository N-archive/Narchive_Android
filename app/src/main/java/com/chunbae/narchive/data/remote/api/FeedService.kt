package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.response.ResponseFeedData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {

    @GET("/diaries")
    suspend fun getDiaryFeedData(
        @Query("page") page : Int
    ) : Response<ResponseFeedData>
}