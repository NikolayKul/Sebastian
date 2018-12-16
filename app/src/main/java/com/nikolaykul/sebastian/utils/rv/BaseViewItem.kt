package com.nikolaykul.sebastian.utils.rv

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes

/**
 * @author NikolayKul
 */
interface BaseViewItem<in TBinding : ViewDataBinding> {
    @get:LayoutRes
    val layoutId: Int

    fun bind(holder: BaseViewHolder<TBinding>)
}
