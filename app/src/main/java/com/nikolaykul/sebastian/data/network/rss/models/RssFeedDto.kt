package com.nikolaykul.sebastian.data.network.rss.models

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import org.joda.time.DateTime

@Xml(name = "item")
class RssFeedDto(

    @PropertyElement(name = "guid")
    var id: String? = null,

    @PropertyElement
    var title: String? = null,

    @PropertyElement
    var description: String? = null,

    @PropertyElement
    var pubDate: DateTime? = null
) {
    override fun toString(): String =
        "RssFeedDto(id='$id', title='$title', description='$description', pubDate=$pubDate)"
}
