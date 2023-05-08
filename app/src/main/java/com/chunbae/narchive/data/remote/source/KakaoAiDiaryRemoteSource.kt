package com.chunbae.narchive.data.remote.source

import android.util.Log
import com.chunbae.narchive.data.data.AiDiaryData
import com.chunbae.narchive.data.remote.api.KakaoAiService
import com.chunbae.narchive.data.remote.api.KakaoService
import com.chunbae.narchive.data.remote.request.RequestAiDiaryData
import com.chunbae.narchive.domain.source.KakaoAiDiarySource
import javax.inject.Inject

class KakaoAiDiaryRemoteSource @Inject constructor(private val kakaoService: KakaoAiService) : KakaoAiDiarySource{

    override suspend fun getKakaoAiDiaryData(prompt: String): Result<AiDiaryData> {
        val aiRes = kakaoService.getAIDiaryData("KakaoAK e53c9019aeb0fc9996de090f883a002e", RequestAiDiaryData(prompt))
        if(aiRes.isSuccessful) {
            return Result.success(aiRes.body()!!.generations[0])
        }
        return Result.failure(IllegalArgumentException())
    }
}