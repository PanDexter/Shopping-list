package com.szeptun.shoppinglist.ui.ShoppingList

import androidx.lifecycle.ViewModel
import com.szeptun.shoppinglist.domain.RemoveProduct
import com.szeptun.shoppinglist.domain.SaveShoppingList
import com.szeptun.shoppinglist.entity.Product
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ShoppingListViewModel @Inject constructor(
    private val saveShoppingList: SaveShoppingList,
    private val removeProduct: RemoveProduct
) : ViewModel() {

    private val onClearDisposable = CompositeDisposable()

    fun saveList(shoppingList: ShoppingList) {
        saveShoppingList.execute(shoppingList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {}
            .addTo(onClearDisposable)
    }

    fun removeProduct(product: Product) {
        removeProduct.execute(product)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(onClearDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        onClearDisposable.clear()
    }
}
