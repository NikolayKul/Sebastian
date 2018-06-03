package com.nikolaykul.sebastian.presentation.feed.list.adapter

import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ItemFeedListBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.rv.BaseViewHolder
import com.nikolaykul.sebastian.utils.rv.BaseViewItem

class FeedListViewItem(
    private val item: RssFeed,
    private val feedClickListener: (RssFeed) -> Unit
) : BaseViewItem<ItemFeedListBinding> {

    override val layoutId = R.layout.item_feed_list

    override fun bind(holder: BaseViewHolder<ItemFeedListBinding>) {
        with(holder.binding) {
            tvDate.text = item.pubDate.toString()
            tvTitle.text = item.title
            tvImage.text = item.imageUrl
            tvSubtitle.text = item.description.slice(0..20)

            root.setOnClickListener { feedClickListener(item) }
        }
    }
}
