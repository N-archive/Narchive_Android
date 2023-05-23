package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestProfileData
import com.chunbae.narchive.data.remote.response.ResponseProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileService {

    @POST("/users/profile")
    suspend fun postUserProfile(
        @Body req : RequestProfileData
    ) : Response<ResponseProfile>
}