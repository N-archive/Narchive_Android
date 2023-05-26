package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.CommentData

interface CommentRepository {

    suspend fun getDiaryComments(diaryIdx : Int) : Result<CommentData>

}