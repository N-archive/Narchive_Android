package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.LocationData

interface KakaoLocationSearchRepository {

    suspend fun getLocationSearch(query : String) : Result<List<LocationData>>
}