package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.remote.request.RequestBookReviewData
import com.chunbae.narchive.data.remote.response.ResponseBookDetailData
import com.chunbae.narchive.data.remote.response.ResponseBookGroupData
import com.chunbae.narchive.data.remote.response.ResponseBookReview

interface BookSource {

    suspend fun postBookReviewData(body : RequestBookReviewData) : Result<ResponseBookReview>

    suspend fun getBookGroupData() : Result<List<ResponseBookGroupData.ResponseBookGroupDataResult>>

    suspend fun getBookDetailData(userReviewIdx : Int) : Result<ResponseBookDetailData.ResponseBookDetailResult>
}