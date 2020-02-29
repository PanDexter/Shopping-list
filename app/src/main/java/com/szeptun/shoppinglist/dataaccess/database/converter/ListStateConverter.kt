package com.szeptun.shoppinglist.dataaccess.database.converter

import androidx.room.TypeConverter
import com.szeptun.shoppinglist.entity.ListState

class ListStateConverter {

    @TypeConverter
    fun fromListState(listState: ListState): Int? = listState.ordinal

    @TypeConverter
    fun toListState(state: Int): ListState = when (state) {
        0 -> ListState.ACTIVE
        else -> ListState.ARCHIVE
    }
}