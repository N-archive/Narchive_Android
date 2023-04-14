package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.remote.response.ResponseBookData

interface KakaoBookSearchRepository {

    suspend fun getBookSearchData(query : String) : Result<List<BookData>>
}