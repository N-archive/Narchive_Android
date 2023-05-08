package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.domain.source.NormalDiarySource

class NormalDiaryRemoteSource : NormalDiarySource {
    override suspend fun postNormalDiary(body: RequestNormalDiaryData): Result<String> {
        TODO("Not yet implemented")
    }
}