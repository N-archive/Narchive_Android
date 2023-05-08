package com.chunbae.narchive

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Narchive : Application() {

    companion object {
        lateinit var mSharedPreferences: SharedPreferences
    }
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        mSharedPreferences = applicationContext.getSharedPreferences("Narchive", MODE_PRIVATE)
        initKakao()

    }

    private fun initKakao() {
        KakaoSdk.init(this, "a8b73ee6826d48109d06c1924f7ba3cb")
    }
}