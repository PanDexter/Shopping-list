package com.szeptun.shoppinglist.domain

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Observable
import javax.inject.Inject

class GetListsByArchive @Inject constructor(private val repository: ShoppingListRepository) {

    fun execute(isArchive: Boolean): Observable<List<ShoppingList>> =
        repository.getListsByArchive(isArchive)
}