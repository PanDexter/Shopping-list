package com.szeptun.shoppinglist.dataaccess.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class ProductsList(
    @Embedded
    val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "shoppingListId",
        entityColumn = "listId"
    )
    val products: List<ProductEntity>
)