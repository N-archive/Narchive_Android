package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.data.remote.api.DiaryService
import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.domain.source.DiarySource
import javax.inject.Inject

class DiaryRemoteSource @Inject constructor(private val service : DiaryService): DiarySource {
    override suspend fun postNormalDiary(body: RequestNormalDiaryData): Result<String> {
        val res = service.postNormalDiary(body)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.body()?.message))
    }

    override suspend fun getDiaryDetailData(diaryIdx: Int): Result<DiaryData> {
        val res = service.getDiaryDetail(diaryIdx)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.body()?.message))
    }
}