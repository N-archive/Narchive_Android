package com.chunbae.narchive.data.remote.source

import android.util.Log
import com.chunbae.narchive.data.remote.api.KakaoService
import com.chunbae.narchive.data.remote.response.ResponseBookData
import com.chunbae.narchive.domain.source.KakaoBookSearchSource
import javax.inject.Inject

class KakaoBookSearchRemoteSource @Inject constructor(val service : KakaoService) : KakaoBookSearchSource {
    override suspend fun getBookSearchData(query : String): Result<List<ResponseBookData.ResultSearchBook>> {
        val bookRes = service.getBookData("KakaoAK e53c9019aeb0fc9996de090f883a002e", query, 1)
        if(bookRes.isSuccessful) {
            bookRes.body()?.let { return Result.success(it.documents) }
        }
        return Result.failure(IllegalArgumentException())
    }
}