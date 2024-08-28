package com.loyalflower.myrecipe.model.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.loyalflower.myrecipe.model.data.recipe.RecipeDao
import com.loyalflower.myrecipe.model.data.recipe.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    // 마이그레이션 스크립트 정의
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE recipe ADD COLUMN favorite INTEGER NOT NULL DEFAULT 0")
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): RecipeDatabase {
        return Room.databaseBuilder(
            appContext,
            RecipeDatabase::class.java,
            "recipe_database"
        ).addMigrations(MIGRATION_1_2).build()
    }

    @Provides
    fun provideRecipeDao(database: RecipeDatabase): RecipeDao {
        return database.recipeDao()
    }
}