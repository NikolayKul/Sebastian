package com.nikolaykul.sebastian.data.utils

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.TypeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Xml factory for [TikXml]
 *
 * @author NikolayKul
 */
object TikXmlFactory {
    fun create(): TikXml = TikXml.Builder()
        .exceptionOnUnreadXml(false)
        .addTypeConverter(DateTime::class.java, DateTimeTypeConverter())
        .build()
}


/**
 * [TypeConverter] for [DateTime]
 *
 * @author NikolayKul
 */
private class DateTimeTypeConverter : TypeConverter<DateTime> {
    private val formatterRfc1123 = DateTimeFormat
        .forPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'")
        .withZoneUTC()

    override fun write(value: DateTime): String = formatterRfc1123.print(value)

    override fun read(value: String): DateTime = formatterRfc1123.parseDateTime(value)

}
