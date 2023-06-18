package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestModifyTodoGroupData
import com.chunbae.narchive.data.remote.request.RequestTodoData
import com.chunbae.narchive.data.remote.request.RequestTodoGroupData
import com.chunbae.narchive.data.remote.response.ResponseCommonWithString
import com.chunbae.narchive.data.remote.response.ResponseTodoGroupData
import com.chunbae.narchive.data.remote.response.ResponseTodoGroupListData
import com.chunbae.narchive.data.remote.response.ResponseTodoListData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TodoService {

    @GET("/todo/groups")
    suspend fun getTodoGroupList() : Response<ResponseTodoGroupListData>

    @POST("/todo/groups")
    suspend fun postTodoGroup(
        @Body body : RequestTodoGroupData
    ) : Response<ResponseTodoGroupData>

    @PATCH("/todo/groups")
    suspend fun patchTodoGroup(
        @Query("todoGroupPK") todoGroupPK : Int
    ) : Response<ResponseCommonWithString>

    @PUT("/todo/groups")
    suspend fun putTodoGroup(
        @Body body : RequestModifyTodoGroupData
    ) : Response<ResponseTodoGroupData>

    @GET("/todo")
    suspend fun getTodos(
        @Query("date") date : String?
    ) : Response<ResponseTodoListData>

    @PATCH("/todo/default")
    suspend fun setDefaultTodoGroup(
        @Query("pastGroupIdx") pastGroupIdx : Int,
        @Query("curGroupIdx") curGroupIdx : Int
    ) : Response<ResponseCommonWithString>

    @GET("/todo/default")
    suspend fun getDefaultTodoGroup()
    : Response<ResponseTodoGroupData>

    @POST("/todo")
    suspend fun postTodo(
        @Body body : RequestTodoData
    ) : Response<ResponseCommonWithString>
}