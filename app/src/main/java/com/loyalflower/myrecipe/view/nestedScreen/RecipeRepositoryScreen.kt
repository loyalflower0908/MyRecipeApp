package com.loyalflower.myrecipe.view.nestedScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loyalflower.myrecipe.view.component.AppBackHandler
import com.loyalflower.myrecipe.view.component.TopBar
import com.loyalflower.myrecipe.view.component.searchRecipeScreenCompo.RecipeItem
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.WhisperColor
import com.loyalflower.myrecipe.viewmodel.RecipeRepositoryViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecipeRepositoryScreen(
    context: Context,
    finish: () -> Unit,
    viewModel: RecipeRepositoryViewModel = hiltViewModel()
) {
    val favoriteRecipeList by viewModel.favoriteRecipes
        .collectAsStateWithLifecycle(initialValue = emptyList())

    //뒤로가기 버튼 관리
    AppBackHandler(context = context, finish = finish)

    //북마크한 레시피 보여주는 UI
    Column(modifier = Modifier.background(WhisperColor)) {
        TopBar(title = "유튜브 보관함")
        HorizontalDivider(color = Color.LightGray.copy(0.3f))
        Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerLarge))
        LazyColumn {
            items(favoriteRecipeList) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    isFavorite = true,
                    onClick = {
                        val url = "https://www.youtube.com/watch?v=${recipe.id}"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    },
                    onFavoriteClick = { isFavorite ->
                        if (isFavorite) viewModel.deleteRecipe(recipe)
                    })
                HorizontalDivider(color = Color.LightGray.copy(0.3f))
            }
        }
    }
}