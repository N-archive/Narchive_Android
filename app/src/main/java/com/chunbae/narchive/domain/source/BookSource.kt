package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.data.remote.response.ResponseBookReview

interface BookSource {

    suspend fun postBookReviewData(body : RequestBookReviewData) : Result<ResponseBookReview>
}