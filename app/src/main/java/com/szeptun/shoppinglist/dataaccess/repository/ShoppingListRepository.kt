package com.szeptun.shoppinglist.dataaccess.repository

import com.szeptun.shoppinglist.dataaccess.database.dao.ItemsListDao
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductEntity
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductsList
import com.szeptun.shoppinglist.dataaccess.database.entity.ShoppingListEntity
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.Product
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ShoppingListRepository @Inject constructor(private val itemsListDao: ItemsListDao) {

    fun getAllLists(): Observable<List<ShoppingList>> =
        itemsListDao.getAllLists()
            .filter { it.isNotEmpty() }
            .map(::mapProductsListToShoppingList)
            .toObservable()

    fun getListsByState(listState: ListState): Observable<List<ShoppingList>> =
        itemsListDao.getListByState(listState)
            .filter { it.isNotEmpty() }
            .map(::mapProductsListToShoppingList)
            .toObservable()

    fun saveList(shoppingList: ShoppingList): Completable {
        val productsList = ProductsList(
            ShoppingListEntity(
                shoppingListId = 0,
                name = shoppingList.name,
                date = shoppingList.date,
                listState = shoppingList.listState
            ),
            products = shoppingList.itemsList.map { ProductEntity(0, 0, it.name) }
        )

        return Completable.fromAction {
            itemsListDao.insertShoppingListWithProducts(productsList)
        }
    }

    private fun mapProductsListToShoppingList(productsListEntity: List<ProductsList>) =
        productsListEntity.map {
            ShoppingList(
                name = it.shoppingList.name,
                listState = it.shoppingList.listState,
                date = it.shoppingList.date,
                itemsList = it.products.map { item -> Product(name = item.name) }
            )
        }
}