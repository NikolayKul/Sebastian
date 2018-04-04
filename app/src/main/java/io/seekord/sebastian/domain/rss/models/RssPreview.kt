package io.seekord.sebastian.domain.rss.models

import java.time.LocalDateTime

/**
 * @author NikolayKul
 */

data class RssPreview(
        val title: String,
        val subtitle: String,
        val createdAt: LocalDateTime
)