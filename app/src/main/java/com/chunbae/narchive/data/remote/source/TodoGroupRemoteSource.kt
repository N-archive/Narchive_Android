package com.chunbae.narchive.data.remote.source

import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.remote.api.TodoService
import com.chunbae.narchive.domain.source.TodoGroupSource
import javax.inject.Inject

class TodoGroupRemoteSource @Inject constructor(private val todoService : TodoService): TodoGroupSource{
    override suspend fun getTodoGroupListData(): Result<List<GroupData>> {
        val res = todoService.getTodoGroupList()
        if (res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }
}