package com.chunbae.narchive.domain.source

import androidx.lifecycle.MutableLiveData
import com.chunbae.narchive.data.remote.response.ResponseFeedData

interface FeedSource {

    suspend fun getFeedData(page : Int) : Result<MutableList<ResponseFeedData.ResponseFeedResult>>
}