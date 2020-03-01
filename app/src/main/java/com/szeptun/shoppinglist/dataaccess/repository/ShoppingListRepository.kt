package com.szeptun.shoppinglist.dataaccess.repository

import com.szeptun.shoppinglist.dataaccess.database.dao.ProductsListDao
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductEntity
import com.szeptun.shoppinglist.dataaccess.database.entity.ProductsList
import com.szeptun.shoppinglist.dataaccess.database.entity.ShoppingListEntity
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.Product
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShoppingListRepository @Inject constructor(private val productsListDao: ProductsListDao) {

    fun getListsByState(listState: ListState): Flowable<List<ShoppingList>> =
        productsListDao.getListByState(listState)
            .map(::mapProductsListToShoppingList)

    fun saveList(shoppingList: ShoppingList): Completable {
        val productsList = ProductsList(
            ShoppingListEntity(
                shoppingListId = shoppingList.id,
                name = shoppingList.name,
                date = shoppingList.date,
                listState = shoppingList.listState
            ),
            products = shoppingList.itemsList.map { ProductEntity(it.id, shoppingList.id, it.name) }
        )

        return Completable.fromAction {
            productsListDao.insertShoppingListWithProducts(productsList)
        }.subscribeOn(Schedulers.io())
    }

    fun removeProduct(product: Product): Completable {
        return Completable.fromAction {
            val productEntity = productsListDao.get(product.id)
            productsListDao.delete(productEntity)
        }.subscribeOn(Schedulers.io())
    }

    private fun mapProductsListToShoppingList(productsListEntity: List<ProductsList>?) =
        productsListEntity?.map {
            ShoppingList(
                id = it.shoppingList.shoppingListId,
                name = it.shoppingList.name,
                listState = it.shoppingList.listState,
                date = it.shoppingList.date,
                itemsList = it.products.map { item ->
                    Product(
                        id = item.productId,
                        name = item.name
                    )
                }
            )
        }?.sortedByDescending { it.date } ?: emptyList()
}