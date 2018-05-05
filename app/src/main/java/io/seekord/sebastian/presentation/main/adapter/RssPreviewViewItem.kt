package io.seekord.sebastian.presentation.main.adapter

import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ItemRssPreviewBinding
import io.seekord.sebastian.domain.rss.models.RssFeed
import io.seekord.sebastian.utils.rv.BaseViewHolder
import io.seekord.sebastian.utils.rv.BaseViewItem

// TODO: rename adapter & ViewItem & layout into "RssFeedViewItem"
class RssPreviewViewItem(private val item: RssFeed) : BaseViewItem<ItemRssPreviewBinding> {

    override val layoutId = R.layout.item_rss_preview

    override fun bind(holder: BaseViewHolder<ItemRssPreviewBinding>) {
        holder.binding.apply {
            tvDate.text = item.date.toString()
            tvTitle.text = item.title
            tvSubtitle.text = item.description.slice(IntRange(0, 10))
        }
    }
}
