package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.data.CommentData

interface CommentSource {

    suspend fun getDiaryComments(diaryIdx : Int) : Result<CommentData>
}