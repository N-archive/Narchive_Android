package com.chunbae.narchive.data.data

data class WriteDialogData (
    val title : String,
    val script : String
        )
fun returnWriteDialogDataList() : List<WriteDialogData> =
    listOf(
        WriteDialogData("간단일기 등록하기" , "키워드 / 사진 / 위치 를 등록할 수 있어요!"),
        WriteDialogData("일기 등록하기" , "글 / 사진 / 위치 를 등록할 수 있어요!"),
        WriteDialogData("책 등록하기" , "인상깊게 읽었던 책을 감상과 함께 등록할 수 있어요!"),
        WriteDialogData("영화 등록하기" , "감명깊게 봤던 영화를 감상과 함께 등록할 수 있어요!")
    )