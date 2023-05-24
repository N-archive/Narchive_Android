package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.remote.api.FeedService
import com.chunbae.narchive.data.remote.response.ResponseFeedData
import com.chunbae.narchive.domain.source.FeedSource
import javax.inject.Inject

class FeedRemoteSource @Inject constructor(private val feedService: FeedService): FeedSource {
    override suspend fun getFeedData(page: Int): Result<MutableList<ResponseFeedData.ResponseFeedResult>> {
        val res = feedService.getDiaryFeedData(page)
        if (res.isSuccessful) {
            return Result.success(res.body()!!.result as MutableList)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }
}