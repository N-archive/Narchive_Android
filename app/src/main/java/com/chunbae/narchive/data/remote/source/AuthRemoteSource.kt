package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.remote.api.AuthService
import com.chunbae.narchive.data.remote.request.RequestSignInData
import com.chunbae.narchive.data.remote.response.ResponseSignInData
import com.chunbae.narchive.domain.source.AuthSource
import javax.inject.Inject

class AuthRemoteSource @Inject constructor(private val authService: AuthService): AuthSource {
    override suspend fun postUserData(requestSignInData: RequestSignInData): Result<ResponseSignInData> {
        val res = authService.postUserData(requestSignInData)
        if(res.isSuccessful) {
            return res.body().let { Result.success(it!!) }
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun getAutoSignIn(): Result<ResponseSignInData> {
        val res = authService.getAuthSignIn()
        if(res.isSuccessful) {
            return Result.success(res.body()!!)
        }
        return Result.failure(IllegalArgumentException())
    }
}