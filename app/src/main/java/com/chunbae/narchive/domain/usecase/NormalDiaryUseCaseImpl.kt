package com.chunbae.narchive.domain.usecase

import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.domain.repository.NormalDiaryRepository
import javax.inject.Inject

class NormalDiaryUseCaseImpl @Inject constructor(private val repo : NormalDiaryRepository) : NormalDiaryUseCase{

    override suspend fun invoke(content: String, locationData: LocationData?): Result<String> {
        return repo.postNormalDiary(content.mapToRequest(locationData))
    }

    private fun String.mapToRequest(locationData: LocationData?) : RequestNormalDiaryData {
        return RequestNormalDiaryData(
            this,
            locationData?.place_name,
            locationData?.road_address_name,
            locationData?.x?.toDouble(),
            locationData?.y?.toDouble()
        )
    }
}