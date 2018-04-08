package io.seekord.sebastian.data.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * @author NikolayKul
 */


@Xml(name = "rss")
class RssPreview {

    @Element
    lateinit var channel: Channel

    override fun toString(): String {
        return "RssPreview(channel=$channel)"
    }


}


@Xml
class Channel {

    @PropertyElement
    lateinit var title: String

    @PropertyElement
    lateinit var link: String

    @PropertyElement
    lateinit var description: String

    override fun toString(): String {
        return "Channel(title='$title', link='$link', description='$description')"
    }


}