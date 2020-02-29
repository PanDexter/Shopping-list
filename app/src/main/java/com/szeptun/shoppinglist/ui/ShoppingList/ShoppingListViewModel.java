package com.szeptun.shoppinglist.ui.ShoppingList;

import com.szeptun.shoppinglist.domain.SaveShoppingList;
import com.szeptun.shoppinglist.entity.ShoppingList;

import javax.inject.Inject;

public class ShoppingListViewModel {
    @Inject
    SaveShoppingList saveShoppingList;

    @Inject
    public ShoppingListViewModel() {
    }

    public void saveList(ShoppingList shoppingList) {
        saveShoppingList.execute(shoppingList);
    }
}
