package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.request.RequestSignInData
import com.chunbae.narchive.data.remote.response.ResponseSignInData
import com.chunbae.narchive.domain.repository.AuthRepository
import com.chunbae.narchive.domain.source.AuthSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authSource: AuthSource): AuthRepository {
    override suspend fun postUserData(requestSignInData: RequestSignInData): Result<ResponseSignInData> {
        return authSource.postUserData(requestSignInData)
    }
}