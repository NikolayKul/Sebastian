package io.seekord.sebastian.presentation.main.adapter

import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ItemMainFeedBinding
import io.seekord.sebastian.domain.rss.models.RssPreview
import io.seekord.sebastian.utils.rv.CommonAdapter
import io.seekord.sebastian.utils.rv.CommonViewHolder
import io.seekord.sebastian.utils.rv.CommonViewItem

/**
 * @author NikolayKul
 */


class RssPreviewAdapter : CommonAdapter<ItemMainFeedBinding, RssPreviewViewItem>() {

    fun setItems(previews: List<RssPreview>) {
        previews.map(::RssPreviewViewItem)
                .let {
                    items.clear()
                    items += it
                    notifyDataSetChanged()
                }
    }

}


class RssPreviewViewItem(private val preview: RssPreview) : CommonViewItem<ItemMainFeedBinding> {

    override val layoutId = R.layout.item_main_feed

    override fun bind(holder: CommonViewHolder<ItemMainFeedBinding>) {
        holder.binding.apply {
            tvDate.text = preview.createdAt.toString()
            tvTitle.text = preview.title
            tvSubtitle.text = preview.subtitle
            executePendingBindings()
        }
    }
}
