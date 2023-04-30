package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.repository.KakaoAiDiaryRepositoryImpl
import com.chunbae.narchive.data.remote.repository.KakaoBookSearchRepositoryImpl
import com.chunbae.narchive.data.remote.repository.KakaoLocationSearchRepositoryImpl
import com.chunbae.narchive.domain.repository.KakaoAiDiaryRepository
import com.chunbae.narchive.domain.repository.KakaoBookSearchRepository
import com.chunbae.narchive.domain.repository.KakaoLocationSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindKakaoBookSearchRepository(kakaoBookSearchRepositoryImpl: KakaoBookSearchRepositoryImpl): KakaoBookSearchRepository

    @Binds
    abstract fun bindKakaoLocationSearchRepository(kakaoLocationSearchRepositoryImpl: KakaoLocationSearchRepositoryImpl) : KakaoLocationSearchRepository

    @Binds
    abstract fun bindKakaoAiDiaryRepository(kakaoAiDiaryRepositoryImpl: KakaoAiDiaryRepositoryImpl) : KakaoAiDiaryRepository
}