package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.response.ResponseBookData

interface KakaoBookSearchSource {

    suspend fun getBookSearchData(query : String) : Result<List<ResponseBookData.ResultSearchBook>>
}