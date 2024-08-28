package com.loyalflower.myrecipe.model.data.searchRecipe

import androidx.room.Database
import androidx.room.RoomDatabase
//수정으로 인한 버전 4
@Database(entities = [SearchRecipeEntity::class], version = 4)
abstract class SearchRecipeDatabase : RoomDatabase() {
    abstract fun searchRecipeDao(): SearchRecipeDao
}