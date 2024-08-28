package com.loyalflower.myrecipe.model.data.searchRecipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: SearchRecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: SearchRecipeEntity)

    @Delete
    suspend fun deleteRecipes(recipes: List<SearchRecipeEntity>)

    // 주어진 id를 가진 레시피가 존재하는지 확인
    @Query("SELECT EXISTS(SELECT 1 FROM search_recipe WHERE id = :recipeId)")
    suspend fun isRecipeExists(recipeId: String): Boolean

    //모든 레시피 가져오기
    @Query("SELECT * FROM search_recipe")
    fun getAllRecipes(): Flow<List<SearchRecipeEntity>>

}

