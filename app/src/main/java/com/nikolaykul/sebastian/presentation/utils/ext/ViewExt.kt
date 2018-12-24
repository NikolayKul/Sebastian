package com.nikolaykul.sebastian.presentation.utils.ext

import android.view.View

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}