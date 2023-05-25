package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData

interface DiarySource {

    suspend fun postNormalDiary(body : RequestNormalDiaryData) : Result<String>

    suspend fun getDiaryDetailData(diaryIdx : Int) : Result<DiaryData>
}