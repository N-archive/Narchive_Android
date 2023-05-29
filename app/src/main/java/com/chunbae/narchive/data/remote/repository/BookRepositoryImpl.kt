package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.data.remote.response.ResponseBookGroupData
import com.chunbae.narchive.data.remote.response.ResponseBookReview
import com.chunbae.narchive.domain.repository.BookRepository
import com.chunbae.narchive.domain.source.BookSource
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookSource: BookSource): BookRepository {
    override suspend fun postBookReviewData(body: RequestBookReviewData): Result<ResponseBookReview> {
        return bookSource.postBookReviewData(body)
    }

    override suspend fun getBookGroupData(): Result<List<ResponseBookGroupData.ResponseBookGroupDataResult>> {
        return bookSource.getBookGroupData()
    }
}