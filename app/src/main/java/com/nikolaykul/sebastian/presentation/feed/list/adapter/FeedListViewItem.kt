package com.nikolaykul.sebastian.presentation.feed.list.adapter

import android.widget.ImageView
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ItemFeedListBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.rv.BaseViewHolder
import com.nikolaykul.sebastian.presentation.base.rv.BaseViewItem
import com.nikolaykul.sebastian.presentation.utils.GlideRequests
import com.nikolaykul.sebastian.presentation.utils.ext.getColorCompat
import org.joda.time.format.DateTimeFormat

class FeedListViewItem(
    private val item: RssFeed,
    private val glide: GlideRequests,
    private val feedClickListener: (RssFeed) -> Unit
) : BaseViewItem<ItemFeedListBinding> {
    private val dateFormatter = DateTimeFormat.shortDateTime()

    override val layoutId = R.layout.item_feed_list

    override fun bind(holder: BaseViewHolder<ItemFeedListBinding>) {
        with(holder.binding) {
            tvDate.text = dateFormatter.print(item.pubDate)
            tvTitle.text = item.title
            tvContent.text = item.description
            ivImage.loadImage()

            root.setOnClickListener { feedClickListener(item) }
        }
    }

    private fun ImageView.loadImage() {
        if (item.imageUrl.isNullOrEmpty()) {
            glide.clear(this)
            setImageDrawable(null)
            setBackgroundColor(context.getColorCompat(R.color.debug_2))
        } else {
            glide.load(item.imageUrl)
                .override(context.resources.getDimensionPixelSize(R.dimen.feed_list_image_size))
                .centerCrop()
                .into(this)
        }
    }
}
