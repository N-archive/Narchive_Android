package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.response.ResponseSignInData

interface SharedPreferenceSource {

    suspend fun saveUserInfo(userInfo : ResponseSignInData) : Result<String>

    suspend fun getUserId() : String

    suspend fun deleteUserJWT()

}