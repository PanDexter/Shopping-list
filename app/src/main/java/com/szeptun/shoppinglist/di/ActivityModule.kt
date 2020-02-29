package com.szeptun.shoppinglist.di

import com.szeptun.shoppinglist.ui.MainActivity
import com.szeptun.shoppinglist.ui.ShoppingList.ShoppingListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindShoppingListFragment(): ShoppingListActivity
}