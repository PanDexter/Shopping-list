package com.szeptun.shoppinglist.dataaccess.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.szeptun.shoppinglist.entity.ListState
import org.joda.time.LocalDateTime

@Entity
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    val shoppingListId: Int = 0,
    val name: String,
    val listState: ListState,
    val date: LocalDateTime
)

fun ProductsList.withShoppingListId(shoppingListId: Int) =
    products.map { it.copy(listId = shoppingListId) }