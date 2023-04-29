package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.domain.repository.KakaoLocationSearchRepository
import com.chunbae.narchive.domain.source.KakaoLocationSearchSource
import javax.inject.Inject

class KakaoLocationSearchRepositoryImpl @Inject constructor(private val source : KakaoLocationSearchSource): KakaoLocationSearchRepository {

    override suspend fun getLocationSearch(query: String): Result<List<LocationData>> {
        return source.getLocationSearch(query)
    }
}