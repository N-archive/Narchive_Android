package com.chunbae.narchive.data.remote.response

import com.chunbae.narchive.data.data.GroupData
import com.chunbae.narchive.presentation.util.BaseResponse

data class ResponseTodoGroupData(
    val result : List<GroupData>
) : BaseResponse()
