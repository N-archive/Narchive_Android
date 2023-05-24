package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestSignInData
import com.chunbae.narchive.data.remote.response.ResponseSignInData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("users/auth/sign-in")
    suspend fun postUserData(
        @Body body : RequestSignInData
    ) : Response<ResponseSignInData>

    @GET("users/auth")
    suspend fun getAuthSignIn() : Response<ResponseSignInData>
}