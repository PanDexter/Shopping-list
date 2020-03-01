package com.szeptun.shoppinglist.domain

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository
import com.szeptun.shoppinglist.entity.Product
import io.reactivex.Completable
import javax.inject.Inject

class RemoveProduct @Inject constructor(private val repository: ShoppingListRepository) {

    fun execute(product: Product): Completable =
        repository.removeProduct(product)
}