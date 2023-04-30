package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestAiDiaryData
import com.chunbae.narchive.data.remote.response.ResponseAiData
import com.chunbae.narchive.data.remote.response.ResponseBookData
import com.chunbae.narchive.data.remote.response.ResponseLocationData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface KakaoService {

    @GET("/v3/search/book")
    suspend fun getBookData(
        @Header("Authorization") authorization : String,
        @Query("query", encoded = true) query : String,
        @Query("page") page : Int,
    ) : Response<ResponseBookData>

    @GET("/v2/local/search/keyword.json")
    suspend fun getLocationData(
        @Header("Authorization") authorization : String,
        @Query("query", encoded = true) query : String
    ) : Response<ResponseLocationData>


}