package com.nikolaykul.sebastian.utils.rv

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * @author NikolayKul
 */
open class BaseViewHolder<out TBinding : ViewDataBinding>(
        val binding: TBinding
) : RecyclerView.ViewHolder(binding.root)
