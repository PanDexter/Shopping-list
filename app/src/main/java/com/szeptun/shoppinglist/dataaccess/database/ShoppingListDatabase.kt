package com.szeptun.shoppinglist.dataaccess.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.szeptun.shoppinglist.dataaccess.database.converter.ListStateConverter
import com.szeptun.shoppinglist.dataaccess.database.converter.LocalDateTimeConverter
import com.szeptun.shoppinglist.dataaccess.database.dao.ItemsListDao
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductEntity
import com.szeptun.shoppinglist.dataaccess.database.entity.ShoppingListEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [ProductEntity::class, ShoppingListEntity::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class, ListStateConverter::class)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ItemsListDao
}