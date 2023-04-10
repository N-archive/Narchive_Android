package com.chunbae.narchive.presentation.util

import android.content.Context
import android.util.TypedValue

object ConvertUtil {

    fun dpToPx(context: Context, dp: Int): Int = dpToPx(context, dp.toFloat()).toInt()

    fun dpToPx(context: Context, dp: Float): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)

}