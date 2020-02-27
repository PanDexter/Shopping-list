package com.szeptun.shoppinglist.entity

import org.joda.time.LocalDateTime


data class ShoppingList(
    val name: String,
    val listState: ListState,
    val date: LocalDateTime,
    val itemsList: List<Product>
)

enum class ListState {
    ACTIVE, ARCHIVE
}