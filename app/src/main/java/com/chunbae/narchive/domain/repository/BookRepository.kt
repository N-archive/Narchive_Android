package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.data.remote.response.ResponseBookGroupData
import com.chunbae.narchive.data.remote.response.ResponseBookReview

interface BookRepository {

    suspend fun postBookReviewData(body : RequestBookReviewData) : Result<ResponseBookReview>

    suspend fun getBookGroupData() : Result<List<ResponseBookGroupData.ResponseBookGroupDataResult>>
}