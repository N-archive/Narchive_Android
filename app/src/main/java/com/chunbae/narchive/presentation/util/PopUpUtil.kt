package com.chunbae.narchive.presentation.util

import android.view.WindowManager
import android.widget.PopupWindow
import androidx.databinding.ViewDataBinding
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ItemPopupAiExplainBinding

class PopUpUtil(private var binding : ViewDataBinding) {

    init {
        when(binding) {
            is ItemPopupAiExplainBinding -> (binding as ItemPopupAiExplainBinding).itemPopupTvExplain.text = binding.root.resources.getString(R.string.what_if_ai_explain)
        }
    }

    fun invoke() : PopupWindow{
        val popupWindow = PopupWindow(
            binding.root,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT, true
        )
        popupWindow.elevation = 1f
        return popupWindow
    }

}