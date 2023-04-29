package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.source.KakaoBookSearchRemoteSource
import com.chunbae.narchive.data.remote.source.KakaoLocationSearchRemoteSource
import com.chunbae.narchive.domain.source.KakaoBookSearchSource
import com.chunbae.narchive.domain.source.KakaoLocationSearchSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindKakaoBookSearchRemoteSource(kakaoBookSearchRemoteSource: KakaoBookSearchRemoteSource): KakaoBookSearchSource

    @Binds
    abstract fun bindKakaoLocationSearchRemoteSource(kakaoLocationSearchRemoteSource: KakaoLocationSearchRemoteSource) : KakaoLocationSearchSource
}