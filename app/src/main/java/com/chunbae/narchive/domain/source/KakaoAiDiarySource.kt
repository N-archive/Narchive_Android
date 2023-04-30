package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.data.AiDiaryData
import com.chunbae.narchive.data.remote.api.KakaoService
import javax.inject.Inject

interface KakaoAiDiarySource {

    suspend fun getKakaoAiDiaryData(prompt : String) : Result<AiDiaryData>
}