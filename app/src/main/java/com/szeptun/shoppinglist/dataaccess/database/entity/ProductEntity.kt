package com.szeptun.shoppinglist.dataaccess.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    val productId: Long,
    val listId: Long,
    val name: String
)