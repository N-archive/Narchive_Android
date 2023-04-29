package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.data.LocationData

interface KakaoLocationSearchSource {

    suspend fun getLocationSearch(query : String) : Result<List<LocationData>>
}