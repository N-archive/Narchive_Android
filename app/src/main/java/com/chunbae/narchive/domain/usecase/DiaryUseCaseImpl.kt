package com.chunbae.narchive.domain.usecase

import com.chunbae.narchive.data.data.FeedData
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.data.data.UserData
import com.chunbae.narchive.data.remote.request.RequestNormalDiaryData
import com.chunbae.narchive.data.remote.response.ResponseFeedData
import com.chunbae.narchive.domain.repository.FeedRepository
import com.chunbae.narchive.domain.repository.NormalDiaryRepository
import javax.inject.Inject

class DiaryUseCaseImpl @Inject constructor(
    private val repo: NormalDiaryRepository,
    private val feedRepo: FeedRepository
) : DiaryUseCase {

    override suspend fun postMapping(
        content: String,
        locationData: LocationData?,
        images: MutableList<String>?
    ): Result<String> {
        return repo.postNormalDiary(content.mapToRequest(locationData, images))
    }

    override suspend fun getMapping(page: Int): Result<MutableList<FeedData>> {
        return feedRepo.getFeedData(page).map { it.mapToFeedData() }
    }


    private fun String.mapToRequest(
        locationData: LocationData?,
        images: MutableList<String>?
    ): RequestNormalDiaryData {
        return RequestNormalDiaryData(
            this,
            locationData?.place_name,
            locationData?.road_address_name,
            locationData?.x?.toDouble(),
            locationData?.y?.toDouble(),
            images
        )
    }

    private fun MutableList<ResponseFeedData.ResponseFeedResult>.mapToFeedData(): MutableList<FeedData> {
        val returnList = mutableListOf<FeedData>()
        this.forEach {
            returnList.add(
                FeedData(
                    feedIdx = it.feedIdx,
                    user = UserData(0, it.profilePath, it.nickName),
                    uploadedAt = it.updatedAt,
                    content = if(it.isSimple == "F") it.content else null,
                    keywords = if(it.isSimple == "T") it.content.split(",") else null,
                    thumbNail = it.thumbnailPath,
                    imageCount = it.imageCount,
                    locationAddress = it.locationName,
                    commentCount = it.commentCount,
                    isSimple = it.isSimple
                )
            )
        }
        return returnList
    }
}