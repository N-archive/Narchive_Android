package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.presentation.di.annotation.KakaoAIRetrofit
import com.chunbae.narchive.presentation.di.annotation.KakaoAuthRetrofit
import com.chunbae.narchive.presentation.di.annotation.KakaoRetrofit
import com.chunbae.narchive.presentation.di.annotation.MovieRetrofit
import com.chunbae.narchive.presentation.di.annotation.NarchiveRetrofit
import com.chunbae.narchive.presentation.util.XAccessTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    companion object {
        val BASE_URL = "http://3.37.9.175/"
        val KAKAO_URL = "https://dapi.kakao.com/"
        val KAKAO_AUTH_URL = "https://kapi.kakao.com/"
        val KAKAO_AI_URL = "https://api.kakaobrain.com/"
        val MOVIE_URL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/"
    }

    @Provides
    @Singleton
    fun provideOkHttpInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    @NarchiveRetrofit
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @KakaoRetrofit
    fun provideKakaoRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KAKAO_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @KakaoAuthRetrofit
    fun provideKakaoAuthRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KAKAO_AUTH_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @KakaoAIRetrofit
    fun provideKakaoAIRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KAKAO_AI_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @MovieRetrofit
    fun provideTmdbRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVIE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}