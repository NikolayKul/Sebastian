package io.seekord.sebastian.data.network.models

import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
class RssChannelDto(
        var title: String,
        var link: String,
        var description: String
) {

    override fun toString(): String {
        return "RssChannelDto(title='$title', link='$link', description='$description')"
    }
}
