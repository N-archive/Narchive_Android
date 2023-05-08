package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.data.AiDiaryData
import com.chunbae.narchive.domain.repository.KakaoAiDiaryRepository
import com.chunbae.narchive.domain.source.KakaoAiDiarySource
import javax.inject.Inject

class KakaoAiDiaryRepositoryImpl @Inject constructor(private val source : KakaoAiDiarySource) : KakaoAiDiaryRepository {
    override suspend fun getKakaoAiDiaryData(prompt: String): Result<AiDiaryData> {
        return source.getKakaoAiDiaryData(prompt)
    }
}