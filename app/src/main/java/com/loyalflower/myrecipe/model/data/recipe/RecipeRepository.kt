package com.loyalflower.myrecipe.model.data.recipe

import kotlinx.coroutines.flow.Flow

//repository 청사진
interface RecipeRepository {

    suspend fun insertRecipe(recipe: RecipeEntity)

    suspend fun updateRecipe(recipe: RecipeEntity)

    suspend fun deleteRecipe(recipe: RecipeEntity)

    suspend fun deleteRecipes(recipes:List<RecipeEntity>)

    suspend fun getRecipeById(id: Int): RecipeEntity?

    suspend fun getRecipesByFoodName(foodName: String): List<RecipeEntity>

    fun getRecipesByCategory(category: String): Flow<List<RecipeEntity>>

    fun getFirstRecipeUriByCategory(category: String): Flow<String?>

    fun getUniqueCategories(): Flow<List<String>>

    fun getAllRecipes(): Flow<List<RecipeEntity>>

    suspend fun deleteAllRecipes()
}