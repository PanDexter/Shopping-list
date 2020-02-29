package com.szeptun.shoppinglist.dataaccess.database.dao

import androidx.room.*
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductEntity
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductsList
import com.szeptun.shoppinglist.dataaccess.database.entity.ShoppingListEntity
import com.szeptun.shoppinglist.dataaccess.database.entity.withShoppingListId
import com.szeptun.shoppinglist.entity.ListState
import io.reactivex.Single

@Dao
abstract class ItemsListDao {

    @Transaction
    @Query("SELECT * FROM ShoppingListEntity")
    abstract fun getAllLists(): Single<List<ProductsList>>

    @Transaction
    @Query("SELECT * FROM ShoppingListEntity WHERE listState =:listState")
    abstract fun getListByState(listState: ListState): Single<List<ProductsList>>

    @Transaction
    @Query("SELECT * FROM ShoppingListEntity WHERE shoppingListId =:shoppingListId")
    abstract fun getShoppingListById(shoppingListId: Int): ProductsList?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertShoppingList(item: ShoppingListEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProducts(item: List<ProductEntity>)

    @Update
    abstract fun update(productEntity: ProductEntity)

    @Update
    abstract fun update(shoppingListEntity: ShoppingListEntity)

    @Transaction
    open fun insertShoppingListWithProducts(item: ProductsList) {
        val shoppingList = getShoppingListById(item.shoppingList.shoppingListId)
        if (shoppingList == null) {
            with(insertShoppingList(item.shoppingList)) {
                insertProducts(item.withShoppingListId(toInt()))
            }
        } else {
            update(item.shoppingList)
            item.products.forEach { update(it) }
        }
    }
}