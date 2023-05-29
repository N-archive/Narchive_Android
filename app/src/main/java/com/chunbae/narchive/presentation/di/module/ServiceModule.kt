package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.data.Comment
import com.chunbae.narchive.data.remote.api.AuthService
import com.chunbae.narchive.data.remote.api.BookService
import com.chunbae.narchive.data.remote.api.CommentService
import com.chunbae.narchive.data.remote.api.DiaryService
import com.chunbae.narchive.data.remote.api.FeedService
import com.chunbae.narchive.data.remote.api.KakaoAiService
import com.chunbae.narchive.data.remote.api.KakaoService
import com.chunbae.narchive.data.remote.api.ProfileService
import com.chunbae.narchive.data.remote.api.TmdbService
import com.chunbae.narchive.presentation.di.annotation.KakaoAIRetrofit
import com.chunbae.narchive.presentation.di.annotation.KakaoRetrofit
import com.chunbae.narchive.presentation.di.annotation.NarchiveRetrofit
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
    fun provideKakaoUserService(@KakaoRetrofit retrofit: Retrofit): KakaoService = retrofit.create(KakaoService::class.java)

    @Provides
    @Singleton
    fun provideTmdbService(@TmdbRetrofit retrofit: Retrofit) : TmdbService = retrofit.create(TmdbService::class.java)

    @Provides
    @Singleton
    fun provideKakaoAiService(@KakaoAIRetrofit retrofit: Retrofit) : KakaoAiService = retrofit.create(KakaoAiService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@NarchiveRetrofit retrofit: Retrofit) : AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideNormalDiaryService(@NarchiveRetrofit retrofit: Retrofit) : DiaryService = retrofit.create(DiaryService::class.java)

    @Provides
    @Singleton
    fun provideProfileService(@NarchiveRetrofit retrofit : Retrofit) : ProfileService = retrofit.create(ProfileService::class.java)

    @Provides
    @Singleton
    fun provideFeedService(@NarchiveRetrofit retrofit: Retrofit) : FeedService = retrofit.create(FeedService::class.java)

    @Provides
    @Singleton
    fun provideCommentService(@NarchiveRetrofit retrofit: Retrofit) : CommentService = retrofit.create(CommentService::class.java)

    @Provides
    @Singleton
    fun provideBookService(@NarchiveRetrofit retrofit: Retrofit) : BookService = retrofit.create(BookService::class.java)

}