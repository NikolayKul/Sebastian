package io.seekord.sebastian.data.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

/**
 * @author NikolayKul
 */

@Xml(name = "rss")
class RssMainDto {

    @Path("channel")
    @Element
    lateinit var items: List<RssItemDto>

    override fun toString(): String {
        return "RssMainDto(items=$items)"
    }

}
