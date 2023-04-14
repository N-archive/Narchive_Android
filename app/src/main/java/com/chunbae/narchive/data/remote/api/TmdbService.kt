package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.response.ResponseMovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("/search/movie")
    suspend fun getMovieData(
        @Query("api_key", encoded = true) apiKey : String,
        @Query("language", encoded = true) language : String,
        @Query("query", encoded = true) query : String,
        @Query("page", encoded = true) page : Int,
    ) : Response<ResponseMovieData>
}