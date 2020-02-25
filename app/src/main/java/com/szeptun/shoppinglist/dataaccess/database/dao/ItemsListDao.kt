package com.szeptun.shoppinglist.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.szeptun.shoppinglist.dataaccess.database.entity.ItemsListEntity
import io.reactivex.Single

@Dao
interface ItemsListDao {

    @Query("SELECT * FROM ShoppingListEntity")
    fun getAllLists(): Single<List<ItemsListEntity>>

    @Query("SELECT * FROM ShoppingListEntity WHERE isArchive =:isArchive")
    fun getListsByArchive(isArchive: Boolean): Single<List<ItemsListEntity>>
}