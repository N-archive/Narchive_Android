package com.chunbae.narchive.domain.source

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData

interface FirebaseStorageSource {

    suspend fun uploadProfileToFirebase(path : Uri) : Uri?

    suspend fun uploadProfileBitmapToFirebase(path : Bitmap) : Uri?

    suspend fun uploadWriteImageToFirebase(imageList : List<String>, downloadURL : MutableLiveData<MutableList<String>>)
}