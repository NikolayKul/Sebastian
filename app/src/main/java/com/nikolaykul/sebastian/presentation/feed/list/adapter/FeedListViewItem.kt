package com.nikolaykul.sebastian.presentation.feed.list.adapter

import android.os.Build
import android.text.Html
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ItemFeedListBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
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
            tvContent.text = SpannableStringBuilder(item.description.fromHtml())
                .apply { clearSpans() }

            setImage(ivImage)

            root.setOnClickListener { feedClickListener(item) }
        }
    }

    private fun setImage(imageView: ImageView) {
        if (item.imageUrl.isNullOrEmpty()) {
            imageView.visibility = View.GONE
        } else {
            Glide.with(imageView)
                .load(item.imageUrl)
                .into(imageView)
        }
    }
}


@Suppress("DEPRECATION")
private fun String.fromHtml() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
    Html.fromHtml(this)
} else {
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
}
