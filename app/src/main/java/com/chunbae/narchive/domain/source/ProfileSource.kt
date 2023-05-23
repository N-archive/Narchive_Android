package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.api.ProfileService
import com.chunbae.narchive.data.remote.request.RequestProfileData
import com.chunbae.narchive.data.remote.response.ResponseProfile
import javax.inject.Inject

interface ProfileSource {
    suspend fun uploadProfileData(body : RequestProfileData) : Result<ResponseProfile>
}