package com.nikolaykul.sebastian.presentation.utils

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideModule : AppGlideModule() {
    override fun isManifestParsingEnabled() = false
}