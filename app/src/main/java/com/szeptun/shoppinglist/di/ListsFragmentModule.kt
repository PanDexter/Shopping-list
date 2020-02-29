package com.szeptun.shoppinglist.di

import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.ui.Lists.ListsFragment
import dagger.Module
import dagger.Provides

@Module
abstract class ListsFragmentModule {

    companion object {
        @Provides
        @JvmStatic
        fun provideListState(fragment: ListsFragment): ListState =
            fragment.getListStateFromBundle()
    }
}