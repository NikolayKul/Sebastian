package io.seekord.sebastian.data.models

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * @author NikolayKul
 */

@Xml(name = "rss")
class RssPreviewDto {

    @Path("channel")
    @PropertyElement
    lateinit var title: String

    @Path("channel")
    @PropertyElement
    lateinit var link: String

    @Path("channel")
    @PropertyElement
    lateinit var description: String

    override fun toString(): String {
        return "RssPreviewDto(title='$title', link='$link', description='$description')"
    }

}
