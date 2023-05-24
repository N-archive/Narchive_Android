package com.chunbae.narchive.domain.usecase

import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.data.data.LocationData

interface DiaryUseCase {

    suspend fun postMapping(content : String, locationData: LocationData?, images : MutableList<String>?) : Result<String>

    suspend fun getMapping(page : Int) : Result<MutableList<FeedData>>
}