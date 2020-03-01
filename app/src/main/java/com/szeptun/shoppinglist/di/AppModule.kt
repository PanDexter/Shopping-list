package com.szeptun.shoppinglist.di

import androidx.room.Room
import com.szeptun.shoppinglist.App
import com.szeptun.shoppinglist.dataaccess.database.ShoppingListDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun providesItemListDao(database: ShoppingListDatabase) = database.productsListDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesDatabase(app: App): ShoppingListDatabase = Room.databaseBuilder(
            app.applicationContext,
            ShoppingListDatabase::class.java,
            "shoppinglistdb"
        ).build()
    }
}