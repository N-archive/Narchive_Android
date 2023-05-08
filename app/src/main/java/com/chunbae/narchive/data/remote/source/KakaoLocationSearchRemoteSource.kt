package com.chunbae.narchive.data.remote.source

import android.util.Log
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.data.remote.api.KakaoService
import com.chunbae.narchive.domain.source.KakaoLocationSearchSource
import javax.inject.Inject

class KakaoLocationSearchRemoteSource @Inject constructor(private val service : KakaoService): KakaoLocationSearchSource {
    override suspend fun getLocationSearch(query: String): Result<List<LocationData>> {
        val locationRes = service.getLocationData("KakaoAK e53c9019aeb0fc9996de090f883a002e", query)
        if(locationRes.isSuccessful) {
            return Result.success(locationRes.body()!!.documents)
        }
        return Result.failure(IllegalArgumentException())
    }
}