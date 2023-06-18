package com.chunbae.narchive.data.data

data class TodoData(
    val date : String,
    val todoList : List<TodoList>
) {
    data class TodoList (
        val todoIdx : Int,
        val startTime : String,
        val endTime : String,
        val todoTitle : String,
        val groupTitle : String,
        val groupColor : String,
        var isClear : Boolean
        )
}
