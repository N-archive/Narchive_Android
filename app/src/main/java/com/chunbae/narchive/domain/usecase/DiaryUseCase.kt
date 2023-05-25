package com.chunbae.narchive.domain.usecase

import com.chunbae.narchive.data.data.DiaryData
import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.data.data.LocationData

interface DiaryUseCase {

    suspend fun postMapping(content : String, locationData: LocationData?, images : MutableList<String>?) : Result<String>

    suspend fun getFeedMapping(page : Int) : Result<MutableList<FeedData>>

    suspend fun getDiaryDetailMapping(diaryIdx : Int) : Result<DiaryData>
}