package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.request.RequestProfileData
import com.chunbae.narchive.data.remote.response.ResponseProfile
import com.chunbae.narchive.domain.repository.ProfileRepository
import com.chunbae.narchive.domain.source.ProfileSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val source : ProfileSource): ProfileRepository {
    override suspend fun uploadProfileData(body: RequestProfileData): Result<ResponseProfile> {
        return source.uploadProfileData(body)
    }
}