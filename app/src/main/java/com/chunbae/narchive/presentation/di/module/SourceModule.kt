package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.source.AuthRemoteSource
import com.chunbae.narchive.data.remote.source.FirebaseStorageRemoteSource
import com.chunbae.narchive.data.remote.source.KakaoAiDiaryRemoteSource
import com.chunbae.narchive.data.remote.source.KakaoBookSearchRemoteSource
import com.chunbae.narchive.data.remote.source.KakaoLocationSearchRemoteSource
import com.chunbae.narchive.data.remote.source.NormalDiaryRemoteSource
import com.chunbae.narchive.domain.source.AuthSource
import com.chunbae.narchive.domain.source.FirebaseStorageSource
import com.chunbae.narchive.domain.source.KakaoAiDiarySource
import com.chunbae.narchive.domain.source.KakaoBookSearchSource
import com.chunbae.narchive.domain.source.KakaoLocationSearchSource
import com.chunbae.narchive.domain.source.NormalDiarySource
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

    @Binds
    abstract fun bindKakaoAiDiaryRemoteSource(kakaoAiDiaryRemoteSource: KakaoAiDiaryRemoteSource) : KakaoAiDiarySource

    @Binds
    abstract fun bindAuthRemoteSource(authRemoteSource: AuthRemoteSource) : AuthSource

    @Binds
    abstract fun bindFirebaseStorageRemoteSource(firebaseStorageRemoteSource: FirebaseStorageRemoteSource) : FirebaseStorageSource

    @Binds
    abstract fun bindNormalDiaryRemoteSource(normalDiaryRemoteSource: NormalDiaryRemoteSource) : NormalDiarySource
}