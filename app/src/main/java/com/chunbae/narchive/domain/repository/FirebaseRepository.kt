package com.chunbae.narchive.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData

interface FirebaseRepository {

    suspend fun uploadProfileToFirebase(path : Uri) : Uri?
    suspend fun uploadProfileBitmapToFirebase(path : Bitmap) : Uri?

    suspend fun uploadWriteImageToFirebase(imageList : List<String>, downloadURL: MutableLiveData<MutableList<String>>)

    suspend fun uploadBookOrMovieImageUriToFirebase(isBook : String, path : Uri) : Uri?

    suspend fun uploadBookOrMovieImageBitmapToFirebase(isBook : String, path : Bitmap) : Uri?
}