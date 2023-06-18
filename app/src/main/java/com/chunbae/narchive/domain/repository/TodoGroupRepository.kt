package com.chunbae.narchive.domain.repository

import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.data.remote.request.RequestModifyTodoGroupData
import com.chunbae.narchive.data.remote.request.RequestTodoData
import com.chunbae.narchive.data.remote.request.RequestTodoGroupData

interface TodoGroupRepository {

    suspend fun getTodoGroupListData() : Result<List<GroupData>>

    suspend fun postTodoGroupData(body : RequestTodoGroupData) : Result<GroupData>

    suspend fun patchTodoGroupData(todoGroupPK : Int) : Result<String>

    suspend fun putTodoGroupData(body : RequestModifyTodoGroupData) : Result<GroupData>

    suspend fun getTodoListData(date : String?) : Result<List<TodoData.TodoList>>

    suspend fun patchDefaultTodoGroup(past : Int, cur : Int) : Result<String>

    suspend fun getDefaultTodoGroup() : Result<GroupData>

    suspend fun postTodo(body : RequestTodoData) : Result<String>
}