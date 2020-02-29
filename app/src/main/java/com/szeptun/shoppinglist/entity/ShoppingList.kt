package com.szeptun.shoppinglist.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.LocalDateTime

@Parcelize
data class ShoppingList(
    val name: String,
    val listState: ListState,
    val date: LocalDateTime,
    val itemsList: List<Product>
) : Parcelable


enum class ListState {
    ACTIVE, ARCHIVE;
}