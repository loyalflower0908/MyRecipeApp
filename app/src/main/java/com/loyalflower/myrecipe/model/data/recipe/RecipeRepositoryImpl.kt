package com.loyalflower.myrecipe.model.data.recipe

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//repository의 기능들 구현
class RecipeRepositoryImpl @Inject constructor(private val recipeDao: RecipeDao) :
    RecipeRepository {
    override suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    override suspend fun updateRecipe(recipe: RecipeEntity) {
        recipeDao.updateRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }

    override suspend fun deleteRecipes(recipes: List<RecipeEntity>) {
        recipeDao.deleteRecipes(recipes)
    }

    override suspend fun getRecipeById(id: Int): RecipeEntity? {
        return recipeDao.getRecipeById(id)
    }

    override suspend fun getRecipesByFoodName(foodName: String): List<RecipeEntity>{
        return recipeDao.getRecipesByFoodName(foodName)
    }

    override fun getRecipesByCategory(category: String): Flow<List<RecipeEntity>>{
        return recipeDao.getRecipesByCategory(category)
    }

    override fun getFirstRecipeUriByCategory(category: String): Flow<String?> {
        return recipeDao.getFirstRecipeUriByCategory(category)
    }

    override fun getUniqueCategories(): Flow<List<String>>{
        return recipeDao.getAllCategories()
            .map { categories ->
                categories
                    .flatMap { it
                        .removeSurrounding("[", "]")
                        .split(",") }
                    .distinct() // 중복 제거
            }
    }

    override fun getAllRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun deleteAllRecipes(){
        recipeDao.deleteAllRecipes()
    }
}