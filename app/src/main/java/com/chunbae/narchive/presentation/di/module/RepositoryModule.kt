package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.repository.AuthRepositoryImpl
import com.chunbae.narchive.data.remote.repository.FeedRepositoryImpl
import com.chunbae.narchive.data.remote.repository.FirebaseRepositoryImpl
import com.chunbae.narchive.data.remote.repository.KakaoAiDiaryRepositoryImpl
import com.chunbae.narchive.data.remote.repository.KakaoBookSearchRepositoryImpl
import com.chunbae.narchive.data.remote.repository.KakaoLocationSearchRepositoryImpl
import com.chunbae.narchive.data.remote.repository.NormalDiaryRepositoryImpl
import com.chunbae.narchive.data.remote.repository.ProfileRepositoryImpl
import com.chunbae.narchive.domain.repository.AuthRepository
import com.chunbae.narchive.domain.repository.FeedRepository
import com.chunbae.narchive.domain.repository.FirebaseRepository
import com.chunbae.narchive.domain.repository.KakaoAiDiaryRepository
import com.chunbae.narchive.domain.repository.KakaoBookSearchRepository
import com.chunbae.narchive.domain.repository.KakaoLocationSearchRepository
import com.chunbae.narchive.domain.repository.NormalDiaryRepository
import com.chunbae.narchive.domain.repository.ProfileRepository
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

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    abstract fun bindFirebaseRepository(firebaseRepositoryImpl: FirebaseRepositoryImpl) : FirebaseRepository

    @Binds
    abstract fun bindNormalDiaryRepository(normalDiaryRepositoryImpl: NormalDiaryRepositoryImpl) : NormalDiaryRepository

    @Binds
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl) : ProfileRepository

    @Binds
    abstract fun bindFeedRepository(feedRepositoryImpl: FeedRepositoryImpl) : FeedRepository
}