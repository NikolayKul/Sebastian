package io.seekord.sebastian.data.db

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime


/**
 * Singleton that contains all [TypeConverter]s for the [SebastianDatabase]
 */
object Converters {

    @TypeConverter
    @JvmStatic
    fun convertDateTimeFrom(value: DateTime?): Long? = value?.millis

    @TypeConverter
    @JvmStatic
    fun convertDateTimeTo(value: Long?): DateTime? = value?.let(::DateTime)

}
