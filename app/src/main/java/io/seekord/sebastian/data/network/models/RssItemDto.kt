package io.seekord.sebastian.data.network.models

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import org.joda.time.DateTime

/**
 * @author NikolayKul
 */

@Xml(name = "item")
class RssItemDto {

    @PropertyElement
    lateinit var title: String

    @PropertyElement
    lateinit var description: String

    @PropertyElement(name = "pubDate")
    lateinit var date: DateTime

    @PropertyElement(name = "guid")
    lateinit var link: String

    override fun toString(): String {
        return "RssItemDto(title='$title', description='$description', date=$date, link='$link')"
    }

}
