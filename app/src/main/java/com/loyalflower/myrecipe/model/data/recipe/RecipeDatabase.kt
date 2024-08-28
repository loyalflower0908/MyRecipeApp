package com.loyalflower.myrecipe.model.data.recipe

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
//수정으로 인한 버전 2, 타입 컨버터 선언해주기
@Database(entities = [RecipeEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}