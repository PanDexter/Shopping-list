package com.szeptun.shoppinglist.dataaccess.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.szeptun.shoppinglist.dataaccess.database.dao.ItemsListDao
import com.szeptun.shoppinglist.dataaccess.database.entity.ItemEntity
import com.szeptun.shoppinglist.dataaccess.database.entity.ItemsListEntity
import com.szeptun.shoppinglist.dataaccess.database.entity.ShoppingListEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [ItemEntity::class, ItemsListEntity::class, ShoppingListEntity::class], version = 1)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ItemsListDao
}