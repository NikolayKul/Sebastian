package com.nikolaykul.sebastian.utils.ext

import android.graphics.Rect

fun Rect.update(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    set(left, top, right, bottom)
}