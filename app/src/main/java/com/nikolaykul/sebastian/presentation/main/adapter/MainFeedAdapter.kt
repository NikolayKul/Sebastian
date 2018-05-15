package com.nikolaykul.sebastian.presentation.main.adapter

import com.nikolaykul.sebastian.utils.rv.BaseAdapter

class MainFeedAdapter : BaseAdapter<MainFeedViewItem>() {

    fun setItems(newItems: List<MainFeedViewItem>) = with(items) {
        clear()
        addAll(newItems)
        notifyDataSetChanged()
    }

}
