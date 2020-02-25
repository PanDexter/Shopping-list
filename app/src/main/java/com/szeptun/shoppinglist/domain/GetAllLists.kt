package com.szeptun.shoppinglist.domain

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class GetAllLists @Inject constructor(private val repository: ShoppingListRepository) {

    fun execute(): Observable<List<ShoppingList>> = repository.getAllLists()
}