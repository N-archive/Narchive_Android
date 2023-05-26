package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.response.ResponseDiaryCommentData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {

    @GET("/diaries/{diaryIdx}/comments")
    suspend fun getDiaryComments(
        @Path("diaryIdx") diaryIdx : Int
    ) : Response<ResponseDiaryCommentData>
}