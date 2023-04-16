package com.chunbae.narchive.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation


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

@BindingAdapter("Detail_Blur_Background")
fun ImageView.setBlur(path : Any?) {
    Glide.with(this)
        .load(path)
        .centerCrop()
        .apply(RequestOptions.bitmapTransform(BlurTransformation(30, 3)))
        .into(this)
}