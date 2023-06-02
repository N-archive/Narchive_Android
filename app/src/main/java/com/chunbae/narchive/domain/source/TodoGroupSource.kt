package com.chunbae.narchive.domain.source

import com.chunbae.narchive.data.data.GroupData

interface TodoGroupSource {

    suspend fun getTodoGroupListData() : Result<List<GroupData>>
}