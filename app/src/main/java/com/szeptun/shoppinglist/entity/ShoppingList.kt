package com.szeptun.shoppinglist.entity

import java.time.LocalDateTime

data class ShoppingList(
    val name: String,
    val isArchive: Boolean,
    val date: LocalDateTime,
    val itemsList: List<Item>
)