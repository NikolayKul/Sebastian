package com.nikolaykul.sebastian.presentation.main.adapter

import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.rv.BaseAdapter

class MainFeedAdapter : BaseAdapter<MainFeedViewItem>() {

    fun setItems(items: List<RssFeed>) {
        items.map(::MainFeedViewItem)
                .let {
                    this.items.clear()
                    this.items += it
                    notifyDataSetChanged()
                }
    }

}
