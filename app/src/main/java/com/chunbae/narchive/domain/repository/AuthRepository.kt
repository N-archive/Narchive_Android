package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.remote.request.RequestSignInData
import com.chunbae.narchive.data.remote.response.ResponseSignInData

interface AuthRepository {

    suspend fun postUserData(requestSignInData: RequestSignInData) : Result<ResponseSignInData>
}