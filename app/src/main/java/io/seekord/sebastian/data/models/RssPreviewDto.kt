package io.seekord.sebastian.data.models

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import org.joda.time.DateTime

/**
 * @author NikolayKul
 */

@Xml(name = "item")
class RssPreviewDto {

    @PropertyElement
    lateinit var title: String

    @PropertyElement(name = "pubDate")
    lateinit var date: DateTime

    @PropertyElement
    lateinit var description: String

    @PropertyElement(name = "guid")
    lateinit var link: String

    override fun toString(): String {
        return "RssPreviewDto(title='$title', date=$date, description='$description', link='$link')"
    }

}
