package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.data.CommentData
import com.chunbae.narchive.data.remote.request.RequestCommentData

interface CommentSource {

    suspend fun getDiaryComments(diaryIdx : Int) : Result<CommentData>

    suspend fun postDiaryComment(diaryIdx : Int, body : RequestCommentData) : Result<String>
}