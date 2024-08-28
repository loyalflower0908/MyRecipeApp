package com.loyalflower.myrecipe.view.component.recipeListScreenCompo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyalflower.myrecipe.model.data.recipe.RecipeEntity
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.viewmodel.RecipeListViewModel

@Composable
fun RecipeGrid(
    recipesByCategory: List<RecipeEntity>,
    isAlbum: Boolean,
    deleteMode: Boolean,
    onClick: (RecipeEntity) -> Unit = {}
) {
    //카테고리에 맞는 레시피 UI Grid
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MyRecipeTheme.dimens.gridItemSpace)
    ) {
        items(recipesByCategory.chunked(2)) { chunkedCategories ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isAlbum) RecipeItemBox(
                    recipe = chunkedCategories[0],
                    deleteMode = deleteMode,
                    onClick
                ) else RecipeItemBar(
                    recipe = chunkedCategories[0],
                    deleteMode = deleteMode,
                    onClick
                )
                Spacer(modifier = Modifier.width(MyRecipeTheme.dimens.gridItemSpace2))
                if (chunkedCategories.size > 1) {
                    if (isAlbum) RecipeItemBox(
                        recipe = chunkedCategories[1],
                        deleteMode = deleteMode,
                        onClick
                    ) else RecipeItemBar(
                        recipe = chunkedCategories[1],
                        deleteMode = deleteMode,
                        onClick
                    )
                } else {
                    if (isAlbum) TransparentRecipeItemBox() else TransparentRecipeItemBar()
                }
            }
        }
    }
}

@Composable
fun RecipeGrid(
    uniqueCategories: List<String>,
    isAlbum: Boolean,
    onClick: (String) -> Unit = {},
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    //카테고리 UI Grid
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MyRecipeTheme.dimens.gridItemSpace)
    ) {
        items(uniqueCategories.chunked(2)) { chunkedCategories ->
            val firstRecipe1 by viewModel.getFirstRecipeUriByCategory(
                chunkedCategories[0]
            ).collectAsState(initial = null)
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isAlbum) RecipeItemBox(
                    categoryName = chunkedCategories[0],
                    recipeImage = firstRecipe1,
                    onClick
                ) else RecipeItemBar(
                    categoryName = chunkedCategories[0],
                    recipeImage = firstRecipe1,
                    onClick
                )
                Spacer(modifier = Modifier.width(MyRecipeTheme.dimens.gridItemSpace2))
                if (chunkedCategories.size > 1) {
                    val firstRecipe2 by viewModel.getFirstRecipeUriByCategory(
                        chunkedCategories[1]
                    ).collectAsState(initial = null)
                    if (isAlbum) RecipeItemBox(
                        categoryName = chunkedCategories[1],
                        recipeImage = firstRecipe2,
                        onClick
                    ) else RecipeItemBar(
                        categoryName = chunkedCategories[1],
                        recipeImage = firstRecipe2,
                        onClick
                    )
                } else {
                    if (isAlbum) TransparentRecipeItemBox() else TransparentRecipeItemBar()
                }
            }
        }
    }
}