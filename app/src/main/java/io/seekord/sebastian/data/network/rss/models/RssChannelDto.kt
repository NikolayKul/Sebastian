package io.seekord.sebastian.data.network.rss.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
class RssChannelDto(

        @Path("channel")
        @PropertyElement
        var title: String? = null,

        @Path("channel")
        @PropertyElement
        var link: String? = null,

        @Path("channel")
        @PropertyElement
        var description: String? = null,

        @Path("channel")
        @Element
        var feeds: List<RssFeedDto>? = null
) {
    override fun toString(): String {
        return "RssChannelDto(title=$title, link=$link, description=$description, feeds=$feeds)"
    }

}
