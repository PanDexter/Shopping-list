package com.szeptun.shoppinglist.dataaccess.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ItemsListEntity(
    @Embedded
    val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "shoppingListId",
        entityColumn = "listId"
    )
    val items: List<ItemEntity>
)