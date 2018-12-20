package com.nikolaykul.sebastian.presentation.feed.list.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ItemFeedListBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.common.getColorCompat
import com.nikolaykul.sebastian.utils.rv.BaseViewHolder
import com.nikolaykul.sebastian.utils.rv.BaseViewItem
import org.joda.time.format.DateTimeFormat

class FeedListViewItem(
    private val item: RssFeed,
    private val feedClickListener: (RssFeed) -> Unit
) : BaseViewItem<ItemFeedListBinding> {
    private val dateFormatter = DateTimeFormat.shortDateTime()

    override val layoutId = R.layout.item_feed_list

    override fun bind(holder: BaseViewHolder<ItemFeedListBinding>) {
        with(holder.binding) {
            tvDate.text = dateFormatter.print(item.pubDate)
            tvTitle.text = item.title
            tvContent.text = item.description

            setImage(ivImage)

            root.setOnClickListener { feedClickListener(item) }
        }
    }

    private fun setImage(imageView: ImageView) {
        val context = imageView.context
        if (item.imageUrl.isNullOrEmpty()) {
            val tmpColor = context.getColorCompat(R.color.debug_2)
            imageView.setBackgroundColor(tmpColor)
        } else {
            val imageSize = context.resources.getDimensionPixelSize(R.dimen.feed_list_image_size)
            Glide.with(imageView)
                .load(item.imageUrl)
                .apply(RequestOptions().override(imageSize).centerCrop())
                .into(imageView)
        }
    }
}
