package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.remote.request.RequestProfileData
import com.chunbae.narchive.data.remote.response.ResponseProfile

interface ProfileRepository {
    suspend fun uploadProfileData(body : RequestProfileData) : Result<ResponseProfile>
}