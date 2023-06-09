package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.response.ResponseMovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovieService {

    @GET("searchMovieList.json")
    suspend fun getMovieData(
        @Query("key", encoded = true) key : String,
        @Query("movieNm", encoded = true) movieNm : String,
        @Query("curPage", encoded = true) curPage : Int,
    ) : Response<ResponseMovieData>
}