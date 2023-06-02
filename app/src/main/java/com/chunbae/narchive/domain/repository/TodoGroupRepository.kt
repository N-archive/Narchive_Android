package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.GroupData

interface TodoGroupRepository {

    suspend fun getTodoGroupListData() : Result<List<GroupData>>
}