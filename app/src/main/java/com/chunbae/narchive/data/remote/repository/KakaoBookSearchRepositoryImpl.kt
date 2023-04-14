package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.data.BookData
import com.chunbae.narchive.data.remote.response.ResponseBookData
import com.chunbae.narchive.data.remote.source.KakaoBookSearchRemoteSource
import com.chunbae.narchive.domain.repository.KakaoBookSearchRepository
import javax.inject.Inject

class KakaoBookSearchRepositoryImpl @Inject constructor(private val source : KakaoBookSearchRemoteSource): KakaoBookSearchRepository {
    override suspend fun getBookSearchData(query : String): Result<List<BookData>> = source.getBookSearchData(query).mapBook()

    private fun Result<List<ResponseBookData.ResultSearchBook>>.mapBook() : Result<List<BookData>>{
        val mapData = mutableListOf<BookData>()
        this.onSuccess { it.apply {
            for(i in it) {
                mapData.add(BookData(null, i.thumbnail, null, i.title, i.authors.joinToString(), i.publisher, i.datetime.substring(0, 10), null, null, null))
            }
        } }
        return Result.success(mapData)
    }
}