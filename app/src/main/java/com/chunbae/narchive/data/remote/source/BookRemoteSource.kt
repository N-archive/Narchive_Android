package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.remote.api.BookService
import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.data.remote.response.ResponseBookReview
import com.chunbae.narchive.domain.source.BookSource
import javax.inject.Inject

class BookRemoteSource @Inject constructor(private val bookService: BookService): BookSource {
    override suspend fun postBookReviewData(body: RequestBookReviewData): Result<ResponseBookReview> {
        val res = bookService.postBookReviewData(body)
        if (res.isSuccessful) {
            return Result.success(res.body()!!)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }
}