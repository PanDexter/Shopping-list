package com.szeptun.shoppinglist.domain

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Completable
import javax.inject.Inject

class SaveShoppingList @Inject constructor(private val repository: ShoppingListRepository) {

    fun execute(shoppingList: ShoppingList): Completable =
        repository.saveList(shoppingList)
}