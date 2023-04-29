package com.chunbae.narchive.presentation.ui.write.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ItemDialogSelectGalleryCameraBinding
import com.chunbae.narchive.presentation.ui.write.book.viewmodel.WriteBookViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GalleryCameraDialog : BottomSheetDialogFragment() {
    private lateinit var binding : ItemDialogSelectGalleryCameraBinding
    private val bookViewModel : WriteBookViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_dialog_select_gallery_camera, container, false)

        initBinding()

        return binding.root
    }

    private fun initBinding() {
        binding.dialog = this
    }

    // 0 - 카메라 / 1 - 갤러리
    fun onItemClicked(position : Int) {
        when(tag) {
            "Book" -> bookViewModel.setGalleryOrCamera(position)
        }

        dismiss()
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        when(tag) {
            "Book" -> bookViewModel.setDialogStateChange()

        }
    }
}