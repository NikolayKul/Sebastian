package io.seekord.sebastian.domain.rss.models

import org.joda.time.DateTime

/**
 * @author NikolayKul
 */

data class RssItem(
        val title: String,
        val subtitle: String,
        val date: DateTime
)