package com.szeptun.shoppinglist.di

import android.content.Context
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
        fun providesItemListDao(database: ShoppingListDatabase) = database.shoppingListDao()

        @Provides
        @JvmStatic
        @Singleton
        fun providesDatabase(app: App): ShoppingListDatabase = Room.databaseBuilder(
            app.applicationContext,
            ShoppingListDatabase::class.java,
            "shoppinglistdb"
        ).build()

        @Provides
        @JvmStatic
        @AppContext
        fun providesAppContext(app: App): Context = app.applicationContext
    }
}