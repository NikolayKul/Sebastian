package com.nikolaykul.sebastian.presentation.feed.list.adapter

import com.nikolaykul.sebastian.presentation.base.rv.BaseAdapter

class FeedListAdapter : BaseAdapter<FeedListViewItem>() {

    fun setItems(newItems: List<FeedListViewItem>) = with(items) {
        clear()
        addAll(newItems)
        notifyDataSetChanged()
    }

}
