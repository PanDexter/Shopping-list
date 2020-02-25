package com.szeptun.shoppinglist.di

import com.szeptun.shoppinglist.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{

    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun bindMainActivity(): MainActivity

}