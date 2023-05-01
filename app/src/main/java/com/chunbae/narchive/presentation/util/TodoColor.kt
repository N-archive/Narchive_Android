package com.chunbae.narchive.presentation.util

import com.chunbae.narchive.R

enum class TodoColor(var res : Int) {
    RED(R.color.color_FF0000),
    ORANGE(R.color.color_FF5722),
    YELLOW(R.color.color_FFF000),
    GREEN(R.color.color_00FF00),
    BLUE(R.color.color_000FFF),
    NAVY(R.color.color_3F51B5),
    PURPLE(R.color.color_673AB7),
    BLACK(R.color.color_000000),
    PINK(R.color.color_E91E63),
    GRAY(R.color.color_888888)
}
fun ReturnColorCode(target : String) : Int {
    return TodoColor.valueOf(target).res
}
