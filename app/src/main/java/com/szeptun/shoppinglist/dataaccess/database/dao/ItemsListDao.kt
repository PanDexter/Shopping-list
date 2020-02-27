package com.szeptun.shoppinglist.dataaccess.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductsListEntity
import com.szeptun.shoppinglist.entity.ListState
import io.reactivex.Single

@Dao
interface ItemsListDao {

    @Query("SELECT * FROM ShoppingListEntity")
    fun getAllLists(): Single<List<ProductsListEntity>>

    @Query("SELECT * FROM ShoppingListEntity WHERE listState =:listState")
    fun getListByState(listState: ListState): Single<List<ProductsListEntity>>
}