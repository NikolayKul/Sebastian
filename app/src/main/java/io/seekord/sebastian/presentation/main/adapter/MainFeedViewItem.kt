package io.seekord.sebastian.presentation.main.adapter

import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ItemMainFeedBinding
import io.seekord.sebastian.domain.rss.models.RssFeed
import io.seekord.sebastian.utils.rv.BaseViewHolder
import io.seekord.sebastian.utils.rv.BaseViewItem

class MainFeedViewItem(private val item: RssFeed) : BaseViewItem<ItemMainFeedBinding> {

    override val layoutId = R.layout.item_main_feed

    override fun bind(holder: BaseViewHolder<ItemMainFeedBinding>) {
        holder.binding.apply {
            tvDate.text = item.date.toString()
            tvTitle.text = item.title
            tvSubtitle.text = item.description.slice(0..20)
        }
    }
}
