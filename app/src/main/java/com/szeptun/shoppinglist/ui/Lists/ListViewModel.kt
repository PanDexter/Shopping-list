package com.szeptun.shoppinglist.ui.Lists

import android.util.Log
import androidx.lifecycle.ViewModel
import com.szeptun.shoppinglist.commons.DummyData
import com.szeptun.shoppinglist.domain.GetListsByState
import com.szeptun.shoppinglist.domain.SaveShoppingList
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val getListsByState: GetListsByState,
    private val listState: ListState
) : ViewModel() {

    private val onClearDisposable = CompositeDisposable()
    private val shoppingListItemsSubject = BehaviorSubject.create<List<ShoppingList>>()

    val shoppingListItemsStream: Observable<List<ShoppingList>>
        get() = shoppingListItemsSubject.observeOn(AndroidSchedulers.mainThread())

    init {
        getShoppingLists()
    }

    private fun getShoppingLists() {
        getListsByState.execute(listState)
            .subscribeOn(Schedulers.io())
            .subscribe({
                shoppingListItemsSubject.onNext(it)
            }, {
                Log.e(LOG_TAG, "Error during fething shopping lists")
            }).addTo(onClearDisposable)
    }

    override fun onCleared() {
        onClearDisposable.clear()
    }

    companion object {
        private val LOG_TAG = ListViewModel::class.java.simpleName
    }

}