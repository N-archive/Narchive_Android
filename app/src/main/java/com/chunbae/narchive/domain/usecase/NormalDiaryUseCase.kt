package com.chunbae.narchive.domain.usecase

import com.chunbae.narchive.data.data.LocationData

interface NormalDiaryUseCase {

    suspend fun invoke(content : String, locationData: LocationData?, images : MutableList<String>?) : Result<String>
}