package io.seekord.sebastian.presentation.main.adapter

import io.seekord.sebastian.domain.rss.models.RssFeed
import io.seekord.sebastian.utils.rv.BaseAdapter

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
