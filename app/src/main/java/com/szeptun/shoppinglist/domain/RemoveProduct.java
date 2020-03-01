package com.szeptun.shoppinglist.domain;

import com.szeptun.shoppinglist.dataaccess.repository.ShoppingListRepository;
import com.szeptun.shoppinglist.entity.Product;

import javax.inject.Inject;

import io.reactivex.Completable;

public class RemoveProduct {

    @Inject
    RemoveProduct(){}

    @Inject
    ShoppingListRepository repository;

    public Completable execute(Product product){
        return repository.removeProduct(product);
    }
}
