package com.chunbae.narchive.data.remote.response

data class ResponseMovieData(
    val page : Int,
    val results : List<ResultSearchMovie>
) {
    data class ResultSearchMovie(
        val title : String,
        val release_date : String,

    )
}
