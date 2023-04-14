package com.chunbae.narchive.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


@BindingAdapter("Common_Image")
fun ImageView.setImage(path : Any?) {
    Glide.with(this).load(path).into(this)
}

@BindingAdapter("Common_Fill_Image")
fun ImageView.setFillImage(path : Any?) {
    Glide.with(this).load(path).centerCrop().into(this)
}
@BindingAdapter("Common_Circle_Image")
fun ImageView.setCircleImage(path : Any) {
    Glide.with(this).load(path).circleCrop().into(this)
}

@BindingAdapter("Common_iv_radius_5")
fun ImageView.setRadius5Image(path : Any) {
    Glide.with(this).load(path).transform(CenterCrop(), RoundedCorners(20)).into(this)
}