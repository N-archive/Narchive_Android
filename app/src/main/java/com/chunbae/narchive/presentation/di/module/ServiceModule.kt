package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.api.KakaoService
import com.chunbae.narchive.data.remote.api.TmdbService
import com.chunbae.narchive.presentation.di.annotation.KakaoRetrofit
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
    fun provideTmdbService(@TmdbRetrofit retrofit: Retrofit) : TmdbService = retrofit.create(TmdbService::class.java)

}