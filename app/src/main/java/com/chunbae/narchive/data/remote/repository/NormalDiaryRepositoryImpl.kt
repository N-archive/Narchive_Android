package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.domain.repository.NormalDiaryRepository

class NormalDiaryRepositoryImpl : NormalDiaryRepository {
    override suspend fun postNormalDiary(body: RequestNormalDiaryData): Result<String> {
        TODO("Not yet implemented")
    }
}