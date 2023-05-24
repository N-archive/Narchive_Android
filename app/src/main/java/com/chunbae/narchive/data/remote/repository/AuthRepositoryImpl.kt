package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.request.RequestSignInData
import com.chunbae.narchive.data.remote.response.ResponseSignInData
import com.chunbae.narchive.domain.repository.AuthRepository
import com.chunbae.narchive.domain.source.AuthSource
import com.chunbae.narchive.domain.source.SharedPreferenceSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authSource: AuthSource, private val sharedSource: SharedPreferenceSource): AuthRepository {
    override suspend fun postUserData(requestSignInData: RequestSignInData): Result<ResponseSignInData> {
        return authSource.postUserData(requestSignInData)
    }

    override suspend fun saveUserInfoInLocal(userInfo: ResponseSignInData): Result<String> {
        return sharedSource.saveUserInfo(userInfo)
    }

    override suspend fun getAutoSignIn(): Result<ResponseSignInData> {
        return authSource.getAutoSignIn()
    }
}