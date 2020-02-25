package com.szeptun.shoppinglist.di

import com.szeptun.shoppinglist.ui.ShoppingList.ShoppingListFragment
import com.szeptun.shoppinglist.ui.Lists.ListsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule{
    @ContributesAndroidInjector
    abstract fun bindShoppingListFragment(): ListsFragment

    @ContributesAndroidInjector
    abstract fun bindShoppingListDetailsFragment(): ShoppingListFragment
}