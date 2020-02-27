package com.szeptun.shoppinglist.dataaccess.database.converter

import androidx.room.TypeConverter
import com.szeptun.shoppinglist.entity.ListState

class ListStateConverter {

    @TypeConverter
    fun fromListState(listState: ListState): String? = listState.name

    @TypeConverter
    fun toListState(state: String): ListState = ListState.valueOf(state)
}