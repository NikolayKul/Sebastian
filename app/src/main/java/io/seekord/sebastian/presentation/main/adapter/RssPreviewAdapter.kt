package io.seekord.sebastian.presentation.main.adapter

import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ItemRssPreviewBinding
import io.seekord.sebastian.domain.rss.models.RssItem
import io.seekord.sebastian.utils.rv.CommonAdapter
import io.seekord.sebastian.utils.rv.CommonViewHolder
import io.seekord.sebastian.utils.rv.CommonViewItem

/**
 * @author NikolayKul
 */


class RssPreviewAdapter : CommonAdapter<ItemRssPreviewBinding, RssPreviewViewItem>() {

    fun setItems(items: List<RssItem>) {
        items.map(::RssPreviewViewItem)
                .let {
                    this.items.clear()
                    this.items += it
                    notifyDataSetChanged()
                }
    }

}


class RssPreviewViewItem(private val item: RssItem) : CommonViewItem<ItemRssPreviewBinding> {

    override val layoutId = R.layout.item_rss_preview

    override fun bind(holder: CommonViewHolder<ItemRssPreviewBinding>) {
        holder.binding.apply {
            tvDate.text = item.date.toString()
            tvTitle.text = item.title
            tvSubtitle.text = item.subtitle
        }
    }
}
