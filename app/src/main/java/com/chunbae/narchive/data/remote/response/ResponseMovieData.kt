package com.chunbae.narchive.data.remote.response

data class ResponseMovieData(
    val movieListResult : Sub
) {
    data class Sub(
        val totCnt : Int,
        val movieList : List<ResultSearchMovie>
    )
    data class ResultSearchMovie(
        val movieCd : String,
        val movieNm : String,
        var openDt : String,
        val genreAlt : String,
        val directors : List<Director>
    )
    data class Director (
        val peopleNm : String
            )
}
