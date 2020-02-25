package com.szeptun.shoppinglist.dataaccess.repository

import com.szeptun.shoppinglist.dataaccess.database.dao.ItemsListDao
import com.szeptun.shoppinglist.dataaccess.database.entity.ItemsListEntity
import com.szeptun.shoppinglist.entity.Item
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Observable
import javax.inject.Inject

class ShoppingListRepository @Inject constructor(private val itemsListDao: ItemsListDao) {

    fun getAllLists(): Observable<List<ShoppingList>> =
        itemsListDao.getAllLists()
            .filter { it.isNotEmpty() }
            .toObservable()
            .map(::mapItemsListToShoppingList)

    fun getListsByArchive(isArchive: Boolean): Observable<List<ShoppingList>> =
        itemsListDao.getListsByArchive(isArchive)
            .filter { it.isNotEmpty() }
            .toObservable()
            .map(::mapItemsListToShoppingList)

    private fun mapItemsListToShoppingList(itemsListEntity: List<ItemsListEntity>) = itemsListEntity.map {
        ShoppingList(
            name = it.shoppingList.name,
            isArchive = it.shoppingList.isArchive,
            date = it.shoppingList.date,
            itemsList = it.items.map { item -> Item(name = item.name, quantity = item.quantity) }
        )
    }
}