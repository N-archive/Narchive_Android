package com.chunbae.narchive.data.remote.response

import com.chunbae.narchive.data.data.TodoData
import com.chunbae.narchive.presentation.util.BaseResponse

data class ResponseTodoListData(
    val result : List<TodoData.TodoList>
) : BaseResponse()
