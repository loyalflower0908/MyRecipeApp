package com.loyalflower.myrecipe.model.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeDao
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchRecipeModule {

    private val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("""
            CREATE TABLE search_recipe_new (
                id TEXT PRIMARY KEY NOT NULL,
                title TEXT NOT NULL,
                thumbnailUrl TEXT NOT NULL,
                description TEXT NOT NULL,
                publishDate TEXT NOT NULL,
                channelName TEXT NOT NULL
            )
        """)

            db.execSQL("""
            INSERT INTO search_recipe_new (id, title, thumbnailUrl, description, publishDate, channelName)
            SELECT id, title, thumbnailUrl, description, 'unknown', 'unknown' FROM search_recipe
        """)

            db.execSQL("DROP TABLE search_recipe")

            db.execSQL("ALTER TABLE search_recipe_new RENAME TO search_recipe")
        }
    }

    @Provides
    @Singleton
    fun provideSearchRecipeDatabase(@ApplicationContext context: Context): SearchRecipeDatabase {
        return Room.databaseBuilder(
            context,
            SearchRecipeDatabase::class.java,
            "search_recipe_database"
        ).addMigrations(MIGRATION_3_4).build()
    }

    @Provides
    fun provideSearchRecipeDao(database: SearchRecipeDatabase): SearchRecipeDao {
        return database.searchRecipeDao()
    }
}
