package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.response.ResponseMovieData
import com.chunbae.narchive.domain.repository.MovieSearchRepository
import com.chunbae.narchive.domain.source.MovieSearchSource
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(private val source: MovieSearchSource): MovieSearchRepository {
    override suspend fun getMovieLists(movieNm: String): Result<List<ResponseMovieData.ResultSearchMovie>> {
        return source.getMovieLists(movieNm)
    }
}