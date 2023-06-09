package com.chunbae.narchive.presentation.di.module

import com.chunbae.narchive.data.remote.source.AuthRemoteSource
import com.chunbae.narchive.data.remote.source.BookRemoteSource
import com.chunbae.narchive.data.remote.source.CommentRemoteSource
import com.chunbae.narchive.data.remote.source.DiaryRemoteSource
import com.chunbae.narchive.data.remote.source.FeedRemoteSource
import com.chunbae.narchive.data.remote.source.FirebaseStorageRemoteSource
import com.chunbae.narchive.data.remote.source.KakaoAiDiaryRemoteSource
import com.chunbae.narchive.data.remote.source.KakaoBookSearchRemoteSource
import com.chunbae.narchive.data.remote.source.KakaoLocationSearchRemoteSource
import com.chunbae.narchive.data.remote.source.MovieSearchRemoteSource
import com.chunbae.narchive.data.remote.source.ProfileRemoteSource
import com.chunbae.narchive.data.remote.source.TodoGroupRemoteSource
import com.chunbae.narchive.domain.source.AuthSource
import com.chunbae.narchive.domain.source.BookSource
import com.chunbae.narchive.domain.source.CommentSource
import com.chunbae.narchive.domain.source.DiarySource
import com.chunbae.narchive.domain.source.FeedSource
import com.chunbae.narchive.domain.source.FirebaseStorageSource
import com.chunbae.narchive.domain.source.KakaoAiDiarySource
import com.chunbae.narchive.domain.source.KakaoBookSearchSource
import com.chunbae.narchive.domain.source.KakaoLocationSearchSource
import com.chunbae.narchive.domain.source.MovieSearchSource
import com.chunbae.narchive.domain.source.ProfileSource
import com.chunbae.narchive.domain.source.TodoGroupSource
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
    abstract fun bindNormalDiaryRemoteSource(DiaryRemoteSource: DiaryRemoteSource) : DiarySource

    @Binds
    abstract fun bindProfileRemoteSource(profileRemoteSource: ProfileRemoteSource) : ProfileSource

    @Binds
    abstract fun bindFeedRemoteSource(feedRemoteSource: FeedRemoteSource) : FeedSource

    @Binds
    abstract fun bindCommentRemoteSource(commentRemoteSource: CommentRemoteSource) : CommentSource

    @Binds
    abstract fun bindBookRemoteSource(bookRemoteSource: BookRemoteSource) : BookSource

    @Binds
    abstract fun bindMovieSearchRemoteSource(movieSearchRemoteSource: MovieSearchRemoteSource) : MovieSearchSource

    @Binds
    abstract fun bindTodoGroupSource (todoGroupRemoteSource: TodoGroupRemoteSource) : TodoGroupSource
}