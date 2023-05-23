package com.chunbae.narchive.data.remote.source

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.chunbae.narchive.domain.source.FirebaseStorageSource
import com.chunbae.narchive.domain.source.SharedPreferenceSource
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Response
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class FirebaseStorageRemoteSource @Inject constructor(private val sharedPrefSource: SharedPreferenceSource) :
    FirebaseStorageSource {

    private val storage = FirebaseStorage.getInstance().reference

    override suspend fun uploadProfileToFirebase(path: Uri) : Uri {
        val storagePath = "Image/${sharedPrefSource.getUserId()}/${path.lastPathSegment}"
        val ref = storage.child(storagePath)
        ref.putFile(path)
        return translateToDownLoadURL(storagePath)
    }

    override suspend fun uploadProfileBitmapToFirebase(path: Bitmap): Uri? {
        val baos = ByteArrayOutputStream()
        path.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val storagePath = "Image/${sharedPrefSource.getUserId()}/${path.generationId}"
        val ref = storage.child(storagePath)
        ref.putBytes(data)
        return translateToDownLoadURL(storagePath)
    }

    override suspend fun uploadWriteImageToFirebase(
        imageList: List<String>,
        downloadURL: MutableLiveData<MutableList<String>>
    ) {
        val linkList = mutableListOf<String>()
        for (uri in imageList) {
            val path = "Image/${sharedPrefSource.getUserId()}/${uri.toUri().lastPathSegment}"
            val ref = storage.child(path)
            ref.putFile(uri.toUri()).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    linkList.add(it.toString())
                    downloadURL.value = linkList
                }
            }.addOnFailureListener {
                Log.d("----", "uploadImageToFirebase: FAIL")
            }
        }
    }

    private fun translateToDownLoadURL(uri : String) : Uri {
        return ("https://firebasestorage.googleapis.com/v0/b/narchive-28009.appspot.com/o/" + uri.replace("/", "%2F") + "?alt=media").toUri()
    }
}