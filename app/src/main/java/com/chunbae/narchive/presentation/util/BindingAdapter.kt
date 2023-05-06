package com.chunbae.narchive.presentation.util

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import jp.wasabeef.glide.transformations.BlurTransformation


@BindingAdapter("Common_Image")
fun ImageView.setImage(path: Any?) {
    Glide.with(this).load(path).into(this)
}

@BindingAdapter("Common_Fill_Image")
fun ImageView.setFillImage(path: Any?) {
    Glide.with(this).load(path).centerCrop().into(this)
}

@BindingAdapter("Common_Circle_Image")
fun ImageView.setCircleImage(path: Any?) {
    Glide.with(this).load(path).circleCrop().into(this)
}

@BindingAdapter("Common_iv_radius_5")
fun ImageView.setRadius5Image(path: Any?) {
    Glide.with(this).load(path).transform(CenterCrop(), RoundedCorners(20)).into(this)
}

@BindingAdapter("Common_iv_radius_10")
fun ImageView.setRadius10Image(path: Any?) {
    Glide.with(this).load(path).transform(CenterCrop(), RoundedCorners(30)).into(this)
}

@BindingAdapter("Detail_Blur_Background")
fun ImageView.setBlur(path: Any?) {
    Glide.with(this)
        .load(path)
        .centerCrop()
        .apply(RequestOptions.bitmapTransform(BlurTransformation(30, 3)))
        .into(this)
}


@BindingAdapter("android:layout_height")
fun TextView.setViewHeightSize(int : Int) {
    this.layoutParams.apply {
        if (this.width > this.height) {
            this.height = 0
        }
    }
}
@BindingAdapter("android:layout_width")
fun TextView.setViewWidthSize(int : Int) {
    this.layoutParams.apply {
        if (this.width < this.height) {
            this.width = 0
        }
    }
}

@BindingAdapter("TodoBackGround")
fun ConstraintLayout.setBackground(color : String?) {
    color?.let { this.setBackgroundResource(ReturnColorCode(it)) }
}