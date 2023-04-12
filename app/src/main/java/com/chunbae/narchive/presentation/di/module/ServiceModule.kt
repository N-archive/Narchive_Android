package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.api.KakaoService
import com.chunbae.narchive.presentation.di.annotation.KakaoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideKakaoUserService(@KakaoRetrofit retrofit: Retrofit): KakaoService = retrofit.create(KakaoService::class.java)

}