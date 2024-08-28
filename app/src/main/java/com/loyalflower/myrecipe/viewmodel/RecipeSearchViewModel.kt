package com.loyalflower.myrecipe.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeEntity
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeSearchViewModel @Inject constructor(private val repository: SearchRecipeRepositoryImpl) : ViewModel() {
    //레시피의 즐겨 찾기값 상태
    private val _recipeFavorites = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val recipeFavorites: StateFlow<Map<String, Boolean>> get() = _recipeFavorites

    //레시피 리스트
    private val _recipes = MutableStateFlow<List<SearchRecipeEntity>>(emptyList())
    val recipes: StateFlow<List<SearchRecipeEntity>> = _recipes.asStateFlow()

    //로딩 중인지 체크
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    //레시피 검색
    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            //API 호출해서 검색어 + 레시피 로 검색해서 결과 저장
            val results = repository.searchRecipes("$query 레시피")
            _recipes.value = results

            // API 호출 후, 검색된 레시피의 즐겨찾기 상태를 업데이트(이미 있는지 확인)
            val favorites = results.associate { recipe ->
                val isFavorite = checkIfRecipeExists(recipe.id)
                recipe.id to isFavorite
            }
            _recipeFavorites.value = favorites

            _isLoading.value = false
        }
    }
    //검색 결과 삭제
    fun resetRecipes(){
        _recipes.value = emptyList()
    }

    // 즐겨찾기 추가 메소드
    fun addFavorite(recipe: SearchRecipeEntity) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
            _recipeFavorites.value += (recipe.id to true)
        }
    }

    //즐겨찾기 해제 메소드(DB에서 Entity 삭제)
    fun deleteFavorite(recipe: SearchRecipeEntity) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
            _recipeFavorites.value -= recipe.id
        }
    }

    //레시피 이미 있는지 확인
    private suspend fun checkIfRecipeExists(recipeId: String): Boolean {
        return repository.isRecipeExists(recipeId)
    }

}
