package com.chunbae.narchive.data.remote.repository

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

    override suspend fun uploadWriteImageToFirebase(
        imageList: List<String>,
        downloadURL: MutableLiveData<MutableList<String>>
    ) {
        firebaseSource.uploadWriteImageToFirebase(imageList, downloadURL)
    }
}