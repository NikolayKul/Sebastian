package com.nikolaykul.sebastian.data.store.rss.remote.mappers

import com.nikolaykul.sebastian.data.network.rss.models.RssFeedDto
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.common.Mapper
import com.nikolaykul.sebastian.utils.common.find
import com.nikolaykul.sebastian.utils.common.fromHtml
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RssFeedToDtoMapper @Inject constructor() : Mapper<RssFeed, RssFeedDto> {
    override fun map(input: RssFeed) = RssFeedDto(
        id = input.id,
        title = input.title,
        description = input.description,
        pubDate = input.pubDate
    )
}


@Reusable
class RssFeedFromDtoMapper @Inject constructor() : Mapper<RssFeedDto, RssFeed> {
    private val imageUrlPattern = Regex("(?<=<img src=\")[^\"]+")
    private val decodedImagePattern = Regex("\\uFFFC")
    private val extraNewLinesPattern = Regex("(^\\n+|\\n+(?=\\n))")

    override fun map(input: RssFeedDto) = RssFeed(
        id = input.id.orEmpty(),
        title = input.title.orEmpty(),
        description = input.description.decodeHtml(),
        imageUrl = findImageUrl(input.description),
        pubDate = input.pubDate
    )

    private fun findImageUrl(description: String?) = description?.find(imageUrlPattern)?.value

    private fun String?.decodeHtml(): String {
        this ?: return ""
        // can join these patterns to reduce traversals but it's gonna be hardly understandable
        return fromHtml()
            .replace(decodedImagePattern, "")
            .replace(extraNewLinesPattern, "")
    }

}
