package io.seekord.sebastian.utils.view

import android.databinding.BindingAdapter
import android.view.View

/**
 * @author NikolayKul
 */

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}