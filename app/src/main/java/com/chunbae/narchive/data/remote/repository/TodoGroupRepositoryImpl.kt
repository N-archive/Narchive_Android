package com.chunbae.narchive.data.remote.repository

import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.remote.request.RequestTodoGroupData
import com.chunbae.narchive.domain.repository.TodoGroupRepository
import com.chunbae.narchive.domain.source.TodoGroupSource
import javax.inject.Inject

class TodoGroupRepositoryImpl @Inject constructor(private val todoGroupSource: TodoGroupSource): TodoGroupRepository {
    override suspend fun getTodoGroupListData(): Result<List<GroupData>> {
        return todoGroupSource.getTodoGroupListData()
    }

    override suspend fun postTodoGroupData(body: RequestTodoGroupData): Result<String> {
        return todoGroupSource.postTodoGroupData(body)
    }
}