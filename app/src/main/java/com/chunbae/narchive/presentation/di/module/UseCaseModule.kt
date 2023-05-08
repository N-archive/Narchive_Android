package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.domain.usecase.NormalDiaryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideNormalDiaryUseCase(normalDiaryUseCase: NormalDiaryUseCase): NormalDiaryUseCase = normalDiaryUseCase
}