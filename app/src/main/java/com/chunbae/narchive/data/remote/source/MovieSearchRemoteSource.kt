package com.chunbae.narchive.data.remote.source

import android.util.Log
import com.chunbae.narchive.data.remote.api.SearchMovieService
import com.chunbae.narchive.data.remote.response.ResponseMovieData
import com.chunbae.narchive.domain.source.MovieSearchSource
import javax.inject.Inject

class MovieSearchRemoteSource @Inject constructor(private val service : SearchMovieService): MovieSearchSource {
    override suspend fun getMovieLists(movieNm: String): Result<List<ResponseMovieData.ResultSearchMovie>> {
        val res = service.getMovieData("f28ad8a4ed81962cbbffbd289eea80b1", movieNm, 1)
        if(res.isSuccessful) {
            Log.d("----", "getMovieLists: ${res.body()!!.movieListResult.movieList}")
            return Result.success(res.body()!!.movieListResult.movieList)
        }
        Log.d("----", "getMovieLists: ${res.message()}")
        return Result.failure(IllegalArgumentException(res.message()))
    }
}