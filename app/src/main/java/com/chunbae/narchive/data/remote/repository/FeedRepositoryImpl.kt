package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.remote.response.ResponseFeedData
import com.chunbae.narchive.domain.repository.FeedRepository
import com.chunbae.narchive.domain.source.FeedSource
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(private val feedSource: FeedSource): FeedRepository {
    override suspend fun getFeedData(page: Int): Result<MutableList<ResponseFeedData.ResponseFeedResult>> {
        return feedSource.getFeedData(page)
    }
}