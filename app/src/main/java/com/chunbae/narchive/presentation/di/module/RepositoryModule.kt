package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.repository.KakaoBookSearchRepositoryImpl
import com.chunbae.narchive.domain.repository.KakaoBookSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindKakaoBookSearchRepository(kakaoBookSearchRepositoryImpl: KakaoBookSearchRepositoryImpl): KakaoBookSearchRepository


}