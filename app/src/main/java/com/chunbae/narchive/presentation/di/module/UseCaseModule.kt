package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.domain.usecase.NormalDiaryUseCase
import com.chunbae.narchive.domain.usecase.NormalDiaryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindNormalDiaryUseCase(normalDiaryUseCaseImpl : NormalDiaryUseCaseImpl): NormalDiaryUseCase
}