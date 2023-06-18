package com.chunbae.narchive.data.remote.repository

import androidx.constraintlayout.widget.Group
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.data.remote.request.RequestModifyTodoGroupData
import com.chunbae.narchive.data.remote.request.RequestTodoGroupData
import com.chunbae.narchive.domain.repository.TodoGroupRepository
import com.chunbae.narchive.domain.source.TodoGroupSource
import javax.inject.Inject

class TodoGroupRepositoryImpl @Inject constructor(private val todoGroupSource: TodoGroupSource): TodoGroupRepository {
    override suspend fun getTodoGroupListData(): Result<List<GroupData>> {
        return todoGroupSource.getTodoGroupListData()
    }

    override suspend fun postTodoGroupData(body: RequestTodoGroupData): Result<GroupData> {
        return todoGroupSource.postTodoGroupData(body)
    }

    override suspend fun patchTodoGroupData(todoGroupPK: Int): Result<String> {
        return todoGroupSource.patchTodoGroupData(todoGroupPK)
    }

    override suspend fun putTodoGroupData(body: RequestModifyTodoGroupData): Result<GroupData> {
        return todoGroupSource.putTodoGroupData(body)
    }

    override suspend fun getTodoListData(date: String?): Result<List<TodoData.TodoList>> {
        return todoGroupSource.getTodoListData(date)
    }

    override suspend fun patchDefaultTodoGroup(past: Int, cur: Int): Result<String> {
        return todoGroupSource.patchDefaultTodoGroup(past, cur)
    }

    override suspend fun getDefaultTodoGroup(): Result<GroupData> {
        return todoGroupSource.getDefaultTodoGroup()
    }


}