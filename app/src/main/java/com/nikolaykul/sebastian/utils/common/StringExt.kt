package com.nikolaykul.sebastian.utils.common

import android.os.Build
import android.text.Html

fun String.find(regex: Regex) = regex.find(this)

@Suppress("DEPRECATION")
fun String.fromHtml(): String =
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        Html.fromHtml(this)
    } else {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    }.toString()
