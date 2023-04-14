package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.source.KakaoBookSearchRemoteSource
import com.chunbae.narchive.domain.source.KakaoBookSearchSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindKakaoBookSearchRemoteSource(kakaoBookSearchRemoteSource: KakaoBookSearchRemoteSource): KakaoBookSearchSource
}