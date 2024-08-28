package com.loyalflower.myrecipe.model.data.searchRecipe

import kotlinx.coroutines.flow.Flow

//repository 청사진
interface SearchRecipeRepository {

    suspend fun searchRecipes(query: String): List<SearchRecipeEntity>

    suspend fun insertRecipe(recipe: SearchRecipeEntity)

    suspend fun deleteRecipe(recipe: SearchRecipeEntity)

    suspend fun deleteRecipes(recipes: List<SearchRecipeEntity>)

    suspend fun isRecipeExists(recipeId: String): Boolean

    fun getAllRecipes(): Flow<List<SearchRecipeEntity>>
}