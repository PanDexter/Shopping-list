package com.szeptun.shoppinglist.dataaccess.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey
    val itemId: Long,
    val listId: Long,
    val name: String,
    val quantity: String
)