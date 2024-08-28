package com.loyalflower.myrecipe.view.component.searchRecipeScreenCompo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeEntity
import com.loyalflower.myrecipe.viewmodel.RecipeSearchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecipeList(
    recipes: List<SearchRecipeEntity>,
    viewModel: RecipeSearchViewModel,
    onRecipeClick: (SearchRecipeEntity) -> Unit
) {
    //검색 레시피 즐겨찾기(북마크)용 변수
    val recipeFavorites by viewModel.recipeFavorites.collectAsStateWithLifecycle()
    LazyColumn {
        items(recipes) { recipe ->
            RecipeItem(
                recipe = recipe,
                isFavorite = recipeFavorites[recipe.id] ?: false,
                onFavoriteClick = { isFavorite ->
                    //즐겨찾기 여부에 따라 북마크 리스트(DB)에 저장
                    if (isFavorite) {
                        viewModel.deleteFavorite(recipe)
                    } else {
                        viewModel.addFavorite(recipe)
                    }
                },
                onClick = { onRecipeClick(recipe) })
            HorizontalDivider(color = Color.LightGray.copy(0.3f))
        }
    }
}
