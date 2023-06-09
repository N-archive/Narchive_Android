package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.remote.response.ResponseMovieData

interface MovieSearchRepository {

    suspend fun getMovieLists(movieNm : String) : Result<List<ResponseMovieData.ResultSearchMovie>>
}