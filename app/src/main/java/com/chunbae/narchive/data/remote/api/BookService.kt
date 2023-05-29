package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.data.remote.response.ResponseBookDetailData
import com.chunbae.narchive.data.remote.response.ResponseBookGroupData
import com.chunbae.narchive.data.remote.response.ResponseBookReview
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookService {

    @POST("/books")
    suspend fun postBookReviewData(
        @Body body : RequestBookReviewData
    ) : Response<ResponseBookReview>

    @GET("/books")
    suspend fun getBookGroupData() : Response<ResponseBookGroupData>

    @GET("/books/{userReviewIdx}")
    suspend fun getBookDetailData(
        @Path("userReviewIdx") userReviewIdx : Int
    ) : Response<ResponseBookDetailData>
}