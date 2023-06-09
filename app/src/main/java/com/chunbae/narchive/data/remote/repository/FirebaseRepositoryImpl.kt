package com.chunbae.narchive.data.remote.repository

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.chunbae.narchive.domain.repository.FirebaseRepository
import com.chunbae.narchive.domain.source.FirebaseStorageSource
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseStorageSource):
    FirebaseRepository {
    override suspend fun uploadProfileToFirebase(path: Uri) : Uri?{
        return firebaseSource.uploadProfileToFirebase(path)
    }

    override suspend fun uploadProfileBitmapToFirebase(path: Bitmap): Uri? {
        return firebaseSource.uploadProfileBitmapToFirebase(path)
    }

    override suspend fun uploadWriteImageToFirebase(
        imageList: List<String>,
        downloadURL: MutableLiveData<MutableList<String>>
    ) {
        firebaseSource.uploadWriteImageToFirebase(imageList, downloadURL)
    }

    override suspend fun uploadBookOrMovieImageUriToFirebase(isBook: String, path: Uri): Uri? {
        return firebaseSource.uploadBookOrMovieImageUriToFirebase(isBook, path)
    }

    override suspend fun uploadBookOrMovieImageBitmapToFirebase(
        isBook: String,
        path: Bitmap
    ): Uri? {
        return firebaseSource.uploadBookOrMovieImageBitmapToFirebase(isBook, path)
    }

}