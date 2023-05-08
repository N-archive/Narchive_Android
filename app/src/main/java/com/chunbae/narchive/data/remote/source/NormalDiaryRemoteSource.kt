package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.remote.api.NormalDiaryService
import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.domain.source.NormalDiarySource
import javax.inject.Inject

class NormalDiaryRemoteSource @Inject constructor(private val service : NormalDiaryService): NormalDiarySource {
    override suspend fun postNormalDiary(body: RequestNormalDiaryData): Result<String> {
        val res = service.postNormalDiary(body)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException())
    }
}