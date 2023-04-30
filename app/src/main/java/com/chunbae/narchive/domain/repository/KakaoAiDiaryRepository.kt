package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.AiDiaryData

interface KakaoAiDiaryRepository {

    suspend fun getKakaoAiDiaryData(prompt : String) : Result<AiDiaryData>
}