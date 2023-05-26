package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.data.CommentData
import com.chunbae.narchive.domain.repository.CommentRepository
import com.chunbae.narchive.domain.source.CommentSource
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val commentSource : CommentSource): CommentRepository {
    override suspend fun getDiaryComments(diaryIdx: Int): Result<CommentData> {
        return commentSource.getDiaryComments(diaryIdx)
    }
}