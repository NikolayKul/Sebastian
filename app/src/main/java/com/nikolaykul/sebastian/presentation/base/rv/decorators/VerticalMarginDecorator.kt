package com.nikolaykul.sebastian.presentation.base.rv.decorators

import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.nikolaykul.sebastian.utils.ext.update

class VerticalMarginDecorator private constructor(
    private var marginPx: Int? = null,
    @DimenRes private val marginRes: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val halfMargin = getMargin(view) / 2
        val position = parent.getChildLayoutPosition(view)
        val itemCount = parent.layoutManager?.itemCount ?: 0
        outRect.update(
            top = if (position > 0) halfMargin else 0,
            bottom = if (position < itemCount) halfMargin else 0
        )
    }

    private fun getMargin(view: View): Int =
        when {
            marginPx != null -> marginPx!!
            marginRes != null -> view.resources.getDimensionPixelSize(marginRes)
                .also { marginPx = it }
            else -> throw IllegalStateException("Empty margin!")
        }

    companion object {
        fun withPixels(marginPx: Int) = VerticalMarginDecorator(marginPx = marginPx)
        fun withDimen(marginRes: Int) = VerticalMarginDecorator(marginRes = marginRes)
    }
}