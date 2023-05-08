package com.chunbae.narchive.data.local.source

import com.chunbae.narchive.Narchive.Companion.mSharedPreferences
import com.chunbae.narchive.data.remote.response.ResponseSignInData
import com.chunbae.narchive.domain.source.SharedPreferenceSource

class SharedPreferenceLocalSource : SharedPreferenceSource {

    override suspend fun saveUserInfo(userInfo : ResponseSignInData): Result<String> {
        try {
            mSharedPreferences.edit().putString("userId", userInfo.userIdx.toString())
                .putString("jwt", userInfo.jwt).apply()
            return Result.success(userInfo.isNew)
        } catch (exception : Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun getUserId(): String {
        return mSharedPreferences.getString("userId", "NULL") ?: "NULL"
    }

    override suspend fun deleteUserJWT() {
        mSharedPreferences.edit().clear().apply()
    }
}