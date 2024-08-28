package com.loyalflower.myrecipe.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loyalflower.myrecipe.model.data.recipe.RecipeEntity
import com.loyalflower.myrecipe.model.data.recipe.RecipeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val repository: RecipeRepositoryImpl) :
    ViewModel() {

        //모든 레시피
    val allRecipes: StateFlow<List<RecipeEntity>> =
        repository.getAllRecipes().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //즐겨찾기 분류를 위한 Boolean
    private val _favoriteFilter = MutableStateFlow(false)
    val favoriteFilter: StateFlow<Boolean> = _favoriteFilter.asStateFlow()

    //중복 없는 카테고리 가져오기 with 즐겨찾기 필터링
    val uniqueCategories: StateFlow<List<String>> = combine(
        allRecipes,
        favoriteFilter
    ) { recipes, filter->
        recipes
            .filter { (if (filter) it.favorite else true) }
            .map { it.categoriesText }
            .flatMap { it.removeSurrounding("[", "]").split(",") }
            .distinct()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //카테고리 클릭 여부
    private val _isClicked = MutableStateFlow(false)
    val isClicked: StateFlow<Boolean> = _isClicked.asStateFlow()

    //삭제 모드
    private val _deleteMode = MutableStateFlow(false)
    val deleteMode: StateFlow<Boolean> = _deleteMode.asStateFlow()

    // 지울 레시피 보관을 위한 Flow
    private val _recipesToDelete = mutableStateListOf<RecipeEntity>()
    //val recipesToDelete:SnapshotStateList<RecipeEntity> get() = _recipesToDelete

    // 카테고리로 레시피를 찾기 위한 Flow
    private val _recipesByCategory = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val recipesByCategory: StateFlow<List<RecipeEntity>> = _recipesByCategory.asStateFlow()

    //DB에 Entity 업데이트
    fun updateRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.updateRecipe(recipe)
        }
    }

    //지울 레시피 리스트에 추가
    fun addRecipeToDelete(recipe: RecipeEntity){
        _recipesToDelete.add(recipe)
    }

    //지울 레시피 리스트에서 삭제
    fun removeRecipeToDelete(recipe: RecipeEntity){
        _recipesToDelete.remove(recipe)
    }

    //DB에서 Entity 삭제
    fun deleteRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }

    //DB에서 지울 레시피 리스트를 참고해서 삭제(삭제 모드에서만 사용. 삭제 후엔 삭제 모드 끄기)
    fun deleteRecipes() {
        viewModelScope.launch {
            repository.deleteRecipes(_recipesToDelete)
        }
        toggleDeleteMode()
    }

    //카테고리를 기반으로 레시피 가져오기(카테고리를 눌렀을 때 작동)
    fun getRecipesByCategory(category: String) {
        viewModelScope.launch {
            repository.getRecipesByCategory(category).combine(favoriteFilter){ recipe, filter ->
                recipe.filter { if(filter) it.favorite else true }
            }.collect{ filteredRecipes ->
                _recipesByCategory.value = filteredRecipes
            }
        }
        toggleIsClicked()
    }

    //카테고리에 음식 미리보기를 위한 카테고리의 첫 번째 음식 사진 Uri 가져오기
    fun getFirstRecipeUriByCategory(category: String): Flow<String?> {
        return repository.getFirstRecipeUriByCategory(category)
    }

    //아래는 Boolean값 toggle 함수들
    fun toggleFavorite() {
        _favoriteFilter.value = !_favoriteFilter.value
    }

    fun toggleIsClicked() {
        _isClicked.value = !_isClicked.value
    }

    fun toggleDeleteMode() {
        _deleteMode.value = !_deleteMode.value
    }

}