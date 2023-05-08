package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.api.AuthService
import com.chunbae.narchive.data.remote.api.KakaoAiService
import com.chunbae.narchive.data.remote.api.KakaoService
import com.chunbae.narchive.data.remote.api.TmdbService
import com.chunbae.narchive.presentation.di.annotation.KakaoAIRetrofit
import com.chunbae.narchive.presentation.di.annotation.KakaoRetrofit
import com.chunbae.narchive.presentation.di.annotation.NarchiveRetrofit
import com.chunbae.narchive.presentation.di.annotation.TmdbRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideKakaoService(@KakaoRetrofit retrofit: Retrofit): KakaoService = retrofit.create(KakaoService::class.java)

    @Provides
    @Singleton
    fun provideKakaoAiService(@KakaoAIRetrofit retrofit : Retrofit) : KakaoAiService = retrofit.create(KakaoAiService::class.java)

    @Provides
    @Singleton
    fun provideTmdbService(@TmdbRetrofit retrofit: Retrofit) : TmdbService = retrofit.create(TmdbService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@NarchiveRetrofit retrofit: Retrofit) : AuthService = retrofit.create(AuthService::class.java)

}