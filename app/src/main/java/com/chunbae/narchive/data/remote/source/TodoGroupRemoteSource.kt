package com.chunbae.narchive.data.remote.source

import androidx.constraintlayout.widget.Group
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.remote.api.TodoService
import com.chunbae.narchive.data.remote.request.RequestModifyTodoGroupData
import com.chunbae.narchive.data.remote.request.RequestTodoGroupData
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

    override suspend fun postTodoGroupData(body: RequestTodoGroupData): Result<GroupData> {
        val res = todoService.postTodoGroup(body)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }

    override suspend fun patchTodoGroupData(todoGroupPK: Int): Result<String> {
        val res = todoService.patchTodoGroup(todoGroupPK)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }

    override suspend fun putTodoGroupData(body: RequestModifyTodoGroupData): Result<GroupData> {
        val res = todoService.putTodoGroup(body)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }
}