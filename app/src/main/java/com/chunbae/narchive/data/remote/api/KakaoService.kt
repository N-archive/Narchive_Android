package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.response.ResponseBookData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoService {

    @GET("/v3/search/book")
    suspend fun getBookData(
        @Header("Authorization") authorization : String,
        @Query("query", encoded = true) query : String,
        @Query("page") page : Int,
    ) : Response<ResponseBookData>
}