package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData

interface NormalDiaryRepository {

    suspend fun postNormalDiary(body : RequestNormalDiaryData) : Result<String>
}