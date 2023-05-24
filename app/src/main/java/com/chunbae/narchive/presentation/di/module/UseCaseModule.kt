package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.domain.usecase.DiaryUseCase
import com.chunbae.narchive.domain.usecase.DiaryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindDiaryUseCase(diaryUseCaseImpl : DiaryUseCaseImpl): DiaryUseCase
}