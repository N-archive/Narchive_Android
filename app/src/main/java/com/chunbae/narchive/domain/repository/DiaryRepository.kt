package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.data.remote.request.RequestDiaryData

interface DiaryRepository {

    suspend fun postNormalDiary(body : RequestDiaryData) : Result<String>

    suspend fun getDiaryDetailData(diaryIdx : Int) : Result<DiaryData>
}