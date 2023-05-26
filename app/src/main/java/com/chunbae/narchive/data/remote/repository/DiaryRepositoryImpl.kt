package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.data.remote.request.RequestDiaryData
import com.chunbae.narchive.domain.repository.DiaryRepository
import com.chunbae.narchive.domain.source.DiarySource
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(private val diarySource: DiarySource):
    DiaryRepository {
    override suspend fun postNormalDiary(body: RequestDiaryData): Result<String> {
        return diarySource.postNormalDiary(body)
    }

    override suspend fun getDiaryDetailData(diaryIdx: Int): Result<DiaryData> {
        return diarySource.getDiaryDetailData(diaryIdx)
    }
}