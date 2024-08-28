package com.loyalflower.myrecipe.model.data.shoppingList

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ShoppingListEntity::class], version = 1)
@TypeConverters(ShoppingListConverters::class)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
}
