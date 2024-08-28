package com.loyalflower.myrecipe.model.di

import android.content.Context
import androidx.room.Room
import com.loyalflower.myrecipe.model.data.shoppingList.ShoppingListDao
import com.loyalflower.myrecipe.model.data.shoppingList.ShoppingListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppingListModule {

    @Provides
    @Singleton
    fun provideShoppingListDatabase(@ApplicationContext context: Context): ShoppingListDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingListDatabase::class.java,
            "shopping_list_database"
        ).build()
    }

    @Provides
    fun provideShoppingListDao(database: ShoppingListDatabase): ShoppingListDao {
        return database.shoppingListDao()
    }
}
