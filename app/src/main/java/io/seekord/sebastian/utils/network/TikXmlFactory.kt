package io.seekord.sebastian.utils.network

import com.tickaroo.tikxml.TikXml

/**
 * Xml factory for [TikXml]
 *
 * @author NikolayKul
 */
object TikXmlFactory {

    fun create(): TikXml = TikXml.Builder()
            .exceptionOnUnreadXml(false)
            .build()

}


