package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.response.ResponseMovieData

interface MovieSearchSource {

    suspend fun getMovieLists(movieNm : String) : Result<List<ResponseMovieData.ResultSearchMovie>>
}