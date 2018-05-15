package com.nikolaykul.sebastian.presentation.main.adapter

import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ItemMainFeedBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.rv.BaseViewHolder
import com.nikolaykul.sebastian.utils.rv.BaseViewItem

class MainFeedViewItem(
        private val item: RssFeed,
        private val feedClickListener: (RssFeed) -> Unit
) : BaseViewItem<ItemMainFeedBinding> {

    override val layoutId = R.layout.item_main_feed

    override fun bind(holder: BaseViewHolder<ItemMainFeedBinding>) {
        with(holder.binding) {
            tvDate.text = item.date.toString()
            tvTitle.text = item.title
            tvSubtitle.text = item.description.slice(0..20)

            root.setOnClickListener { feedClickListener(item) }
        }
    }
}
