package com.szeptun.shoppinglist.dataaccess.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
    val listId: Int,
    val name: String
)