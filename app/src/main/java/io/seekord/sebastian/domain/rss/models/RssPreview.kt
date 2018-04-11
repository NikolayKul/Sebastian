package io.seekord.sebastian.domain.rss.models

import org.joda.time.DateTime

/**
 * @author NikolayKul
 */

data class RssPreview(
        val title: String,
        val subtitle: String,
        val createdAt: DateTime
)