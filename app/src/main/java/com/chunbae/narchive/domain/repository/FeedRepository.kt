package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.remote.response.ResponseFeedData

interface FeedRepository {

    suspend fun getFeedData(page : Int) : Result<MutableList<ResponseFeedData.ResponseFeedResult>>
}