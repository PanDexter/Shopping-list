package com.szeptun.shoppinglist.domain;

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository;
import com.szeptun.shoppinglist.entity.ListState;
import com.szeptun.shoppinglist.entity.ShoppingList;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetListsByState {

    @Inject
    GetListsByState() {
    }

    @Inject
    ShoppingListRepository repository;

    public Flowable<List<ShoppingList>> execute(ListState listState) {
        return repository.getListsByState(listState);
    }
}
