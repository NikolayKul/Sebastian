package com.nikolaykul.sebastian.data.store.rss.remote.factory

import com.nikolaykul.sebastian.data.network.rss.models.RssChannelDto
import timber.log.Timber

object RssChannelIdFactory {
    private const val CHANNEL_ID_TEMPLATE = "id_%s"
    private const val CHANNEL_ID_DEFAULT = "id_error"

    fun createId(dto: RssChannelDto): String = when {
        dto.link != null -> CHANNEL_ID_TEMPLATE.format(dto.link)
        dto.title != null -> CHANNEL_ID_TEMPLATE.format(dto.title)
        else -> {
            Timber.w("Can\'t create an `id` for $dto")
            CHANNEL_ID_DEFAULT
        }
    }

}