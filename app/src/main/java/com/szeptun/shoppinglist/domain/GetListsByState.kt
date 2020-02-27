package com.szeptun.shoppinglist.domain

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Observable
import javax.inject.Inject

class GetListsByState @Inject constructor(private val repository: ShoppingListRepository) {

    fun execute(listState: ListState): Observable<List<ShoppingList>> =
        repository.getListsByState(listState)
}