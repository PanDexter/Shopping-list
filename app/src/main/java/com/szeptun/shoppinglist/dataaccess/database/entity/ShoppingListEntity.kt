package com.szeptun.shoppinglist.dataaccess.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class ShoppingListEntity(
    @PrimaryKey
    val shoppingListId: Long,
    val name: String,
    val isArchive: Boolean,
    val date: LocalDateTime
)