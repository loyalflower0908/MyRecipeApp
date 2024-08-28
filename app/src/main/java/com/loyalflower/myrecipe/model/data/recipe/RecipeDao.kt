package com.loyalflower.myrecipe.model.data.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {
    //있을 경우 대체(Replace)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipes(recipes: List<RecipeEntity>)

    //Entity table 이름 recipe
    //id를 통해 레시피 가져오기
    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeEntity?

    //foodName을 통해 레시피 가져오기
    @Query("SELECT * FROM recipe WHERE foodName = :foodName")
    suspend fun getRecipesByFoodName(foodName: String): List<RecipeEntity>

    //category를 통해 레시피 가져오기
    @Query("SELECT * FROM recipe WHERE categoriesText LIKE '%' || :category || '%'")
    fun getRecipesByCategory(category: String): Flow<List<RecipeEntity>>

    //category를 통해 첫번째 uri 가져오기
    @Query("SELECT imageUri FROM recipe WHERE categoriesText LIKE '%' || :category || '%' LIMIT 1")
    fun getFirstRecipeUriByCategory(category: String): Flow<String?>

    //모든 레시피의 categoriesText(String) 가져오기
    @Query("SELECT categoriesText FROM recipe")
    fun getAllCategories(): Flow<List<String>>

    //모든 레시피 가져오기
    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    //모두 삭제
    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipes()

}