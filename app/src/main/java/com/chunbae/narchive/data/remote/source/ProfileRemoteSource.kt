package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.remote.api.ProfileService
import com.chunbae.narchive.data.remote.request.RequestProfileData
import com.chunbae.narchive.data.remote.response.ResponseProfile
import com.chunbae.narchive.domain.source.ProfileSource
import javax.inject.Inject

class ProfileRemoteSource @Inject constructor(private val service : ProfileService) : ProfileSource {
    override suspend fun uploadProfileData(body: RequestProfileData): Result<ResponseProfile> {
        val res = service.postUserProfile(body)
        if (res.isSuccessful) {
            return Result.success(res.body()!!)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }
}