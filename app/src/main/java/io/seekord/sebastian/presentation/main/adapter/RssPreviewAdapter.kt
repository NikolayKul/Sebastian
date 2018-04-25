package io.seekord.sebastian.presentation.main.adapter

import io.seekord.sebastian.domain.rss.models.RssItem
import io.seekord.sebastian.utils.rv.BaseAdapter

/**
 * @author NikolayKul
 */

class RssPreviewAdapter : BaseAdapter<RssPreviewViewItem>() {

    fun setItems(items: List<RssItem>) {
        items.map(::RssPreviewViewItem)
                .let {
                    this.items.clear()
                    this.items += it
                    notifyDataSetChanged()
                }
    }

}
