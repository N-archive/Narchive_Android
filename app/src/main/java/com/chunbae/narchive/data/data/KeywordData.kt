package com.chunbae.narchive.data.data

data class KeywordData(
    val keywordTitle : String,
    var keywordItems : List<Keywords>?,
) {
    data class Keywords (
        val itemTitle : String,
        var isClicked : Boolean
            )
}
