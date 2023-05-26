package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestCommentData
import com.chunbae.narchive.data.remote.response.ResponseDiaryComment
import com.chunbae.narchive.data.remote.response.ResponseDiaryCommentData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {

    @GET("/diaries/{diaryIdx}/comments")
    suspend fun getDiaryComments(
        @Path("diaryIdx") diaryIdx : Int
    ) : Response<ResponseDiaryCommentData>

    @POST("/diaries/{diaryIdx}/comments")
    suspend fun postDiaryComments(
        @Path("diaryIdx") diaryIdx: Int,
        @Body body : RequestCommentData
    ) : Response<ResponseDiaryComment>
}