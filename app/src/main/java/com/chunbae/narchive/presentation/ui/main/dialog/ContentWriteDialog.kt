package com.chunbae.narchive.presentation.ui.main.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.data.data.returnWriteDialogDataList
import com.chunbae.narchive.databinding.DialogContentWriteBinding
import com.chunbae.narchive.presentation.ui.main.MainViewModel
import com.chunbae.narchive.presentation.ui.main.dialog.adapter.ContentWriteAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ContentWriteDialog : BottomSheetDialogFragment() {
    private lateinit var binding : DialogContentWriteBinding
    private val viewModel : MainViewModel by activityViewModels()
    private val writeAdapter by lazy {
        ContentWriteAdapter(returnWriteDialogDataList())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogContentWriteBinding.inflate(inflater, container, false)

        initItems()

        return binding.root
    }

    private fun initItems() {
        binding.dialogContentWriteRvSelector.adapter = writeAdapter

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.changeDialogOpenedState()
    }
}