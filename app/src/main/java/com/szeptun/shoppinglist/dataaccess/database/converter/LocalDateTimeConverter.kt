package com.szeptun.shoppinglist.dataaccess.database.converter

import androidx.room.TypeConverter
import org.joda.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime): String? = localDateTime.toString()

    @TypeConverter
    fun toLocalDateTime(date: String): LocalDateTime = LocalDateTime.parse(date)
}