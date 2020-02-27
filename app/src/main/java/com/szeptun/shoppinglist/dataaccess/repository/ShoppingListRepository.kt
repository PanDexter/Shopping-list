package com.szeptun.shoppinglist.dataaccess.repository

import com.szeptun.shoppinglist.dataaccess.database.dao.ItemsListDao
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductsListEntity
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.Product
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Observable
import javax.inject.Inject

class ShoppingListRepository @Inject constructor(private val itemsListDao: ItemsListDao) {

    fun getAllLists(): Observable<List<ShoppingList>> =
        itemsListDao.getAllLists()
            .filter { it.isNotEmpty() }
            .toObservable()
            .map(::mapProductsListToShoppingList)

    fun getListsByState(listState: ListState): Observable<List<ShoppingList>> =
        itemsListDao.getListByState(listState)
            .filter { it.isNotEmpty() }
            .toObservable()
            .map(::mapProductsListToShoppingList)

    private fun mapProductsListToShoppingList(productsListEntity: List<ProductsListEntity>) = productsListEntity.map {
        ShoppingList(
            name = it.shoppingList.name,
            listState = it.shoppingList.listState,
            date = it.shoppingList.date,
            itemsList = it.products.map { item -> Product(name = item.name) }
        )
    }
}