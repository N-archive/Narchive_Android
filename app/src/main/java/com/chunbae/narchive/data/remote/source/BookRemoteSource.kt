package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.remote.api.BookService
import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.data.remote.response.ResponseBookGroupData
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

    override suspend fun getBookGroupData(): Result<List<ResponseBookGroupData.ResponseBookGroupDataResult>> {
        val res = bookService.getBookGroupData()
        if (res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }
}