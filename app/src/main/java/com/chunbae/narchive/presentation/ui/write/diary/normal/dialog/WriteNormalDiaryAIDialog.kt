package com.chunbae.narchive.presentation.ui.write.diary.normal.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.DialogAiWriteDiaryBinding
import com.chunbae.narchive.presentation.ui.write.diary.normal.viewmodel.WriteNormalDiaryViewModel
import com.chunbae.narchive.presentation.util.LoadingDialog

class WriteNormalDiaryAIDialog : DialogFragment() {
    private lateinit var binding: DialogAiWriteDiaryBinding
    private val viewModel: WriteNormalDiaryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_ai_write_diary, container, false)

        initBinding()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.dialog = this
    }

    private fun changeDialogState() {
        viewModel.changeDialogOpenedState()
    }

    fun onAccept() {
        viewModel.onAcceptAiGenerated()
        onDismiss()
    }

    fun onDismiss() {
        viewModel.initAiGenerated()
        dismiss()
    }

    override fun onResume() {
        super.onResume()
        val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.9).toInt()
        params?.height = LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.changeDialogOpenedState()
    }
}