package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.request.RequestSignInData
import com.chunbae.narchive.data.remote.response.ResponseSignInData

interface AuthSource {

    suspend fun postUserData(requestSignInData: RequestSignInData) : Result<ResponseSignInData>

    suspend fun getAutoSignIn() : Result<ResponseSignInData>
}