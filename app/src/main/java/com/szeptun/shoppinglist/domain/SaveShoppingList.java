package com.szeptun.shoppinglist.domain;

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository;
import com.szeptun.shoppinglist.entity.ShoppingList;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SaveShoppingList {

    @Inject
    SaveShoppingList() {
    }

    @Inject
    ShoppingListRepository repository;

    public Completable execute(ShoppingList shoppingList) {
        return repository.saveList(shoppingList);
    }
}
