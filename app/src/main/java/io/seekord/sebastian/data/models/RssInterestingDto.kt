package io.seekord.sebastian.data.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

/**
 * @author NikolayKul
 */

@Xml(name = "rss")
class RssInterestingDto {

    @Path("channel")
    @Element
    lateinit var previews: List<RssPreviewDto>

    override fun toString(): String {
        return "RssInterestingDto(previews=$previews)"
    }

}
