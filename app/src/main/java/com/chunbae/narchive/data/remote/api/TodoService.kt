package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.response.ResponseTodoGroupData
import retrofit2.Response
import retrofit2.http.GET

interface TodoService {

    @GET("/todo/groups")
    suspend fun getTodoGroupList() : Response<ResponseTodoGroupData>
}