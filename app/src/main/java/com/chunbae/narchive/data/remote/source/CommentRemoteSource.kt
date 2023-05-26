package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.data.CommentData
import com.chunbae.narchive.data.remote.api.CommentService
import com.chunbae.narchive.data.remote.request.RequestCommentData
import com.chunbae.narchive.domain.source.CommentSource
import javax.inject.Inject

class CommentRemoteSource @Inject constructor(private val commentService: CommentService): CommentSource {
    override suspend fun getDiaryComments(diaryIdx: Int): Result<CommentData> {
        val res = commentService.getDiaryComments(diaryIdx)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }

    override suspend fun postDiaryComment(diaryIdx: Int, body: RequestCommentData): Result<String> {
        val res = commentService.postDiaryComments(diaryIdx, body)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }


}