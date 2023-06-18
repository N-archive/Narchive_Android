package com.chunbae.narchive.data.remote.source

import android.util.Log
import androidx.constraintlayout.widget.Group
import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.data.remote.api.TodoService
import com.chunbae.narchive.data.remote.request.RequestModifyTodoGroupData
import com.chunbae.narchive.data.remote.request.RequestTodoData
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

    override suspend fun getTodoListData(date: String?): Result<List<TodoData.TodoList>> {
        val res = todoService.getTodos(date)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun patchDefaultTodoGroup(past: Int, cur: Int): Result<String> {
        val res = todoService.setDefaultTodoGroup(past, cur)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }

    override suspend fun getDefaultTodoGroup(): Result<GroupData> {
        val res = todoService.getDefaultTodoGroup()
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }

    override suspend fun postTodo(body: RequestTodoData): Result<String> {
        val res = todoService.postTodo(body)
        if(res.isSuccessful) {
            return Result.success(res.body()!!.result)
        }
        return Result.failure(IllegalArgumentException(res.message()))
    }
}