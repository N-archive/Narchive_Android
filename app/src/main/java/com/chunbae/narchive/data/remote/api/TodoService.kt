package com.chunbae.narchive.data.remote.api

import com.chunbae.narchive.data.remote.request.RequestTodoGroupData
import com.chunbae.narchive.data.remote.response.ResponseCommonWithString
import com.chunbae.narchive.data.remote.response.ResponseTodoGroupData
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoService {

    @GET("/todo/groups")
    suspend fun getTodoGroupList() : Response<ResponseTodoGroupData>

    @POST("/todo/groups")
    suspend fun postTodoGroup(
        @Body body : RequestTodoGroupData
    ) : Response<ResponseCommonWithString>

    @PATCH("/todo/groups")
    suspend fun patchTodoGroup(
        @Query("todoGroupPK") todoGroupPK : Int
    ) : Response<ResponseCommonWithString>
}