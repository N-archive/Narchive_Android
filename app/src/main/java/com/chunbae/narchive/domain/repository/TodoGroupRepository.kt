package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.remote.request.RequestTodoGroupData

interface TodoGroupRepository {

    suspend fun getTodoGroupListData() : Result<List<GroupData>>

    suspend fun postTodoGroupData(body : RequestTodoGroupData) : Result<String>

    suspend fun patchTodoGroupData(todoGroupPK : Int) : Result<String>
}