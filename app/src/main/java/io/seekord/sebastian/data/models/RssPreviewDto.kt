package io.seekord.sebastian.data.models

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/**
 * @author NikolayKul
 */

@Xml(name = "item")
class RssPreviewDto {

    @PropertyElement
    lateinit var title: String

    @PropertyElement(name = "guid")
    lateinit var link: String

    @PropertyElement
    lateinit var description: String

    override fun toString(): String {
        return "RssPreviewDto(title='$title', link='$link', description='$description')"
    }

}
