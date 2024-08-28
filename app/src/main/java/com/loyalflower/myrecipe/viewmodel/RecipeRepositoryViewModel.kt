package com.loyalflower.myrecipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeEntity
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeRepositoryViewModel @Inject constructor(private val repository: SearchRecipeRepositoryImpl) : ViewModel() {

    // 모든 즐겨찾기 레시피를 가져오는 StateFlow
    val favoriteRecipes: StateFlow<List<SearchRecipeEntity>> = repository.getAllRecipes()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // 즐겨찾기 레시피 삭제 메소드
    fun deleteRecipe(recipe: SearchRecipeEntity) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }
}