package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.domain.repository.NormalDiaryRepository
import com.chunbae.narchive.domain.source.NormalDiarySource
import javax.inject.Inject

class NormalDiaryRepositoryImpl @Inject constructor(private val normalDiarySource: NormalDiarySource): NormalDiaryRepository {
    override suspend fun postNormalDiary(body: RequestNormalDiaryData): Result<String> {
        return normalDiarySource.postNormalDiary(body)
    }
}