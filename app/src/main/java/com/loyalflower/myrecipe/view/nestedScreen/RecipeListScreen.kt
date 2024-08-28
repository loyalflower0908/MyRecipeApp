package com.loyalflower.myrecipe.view.nestedScreen

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loyalflower.myrecipe.view.component.AppBackHandler
import com.loyalflower.myrecipe.view.component.recipeListScreenCompo.BoxOrBar
import com.loyalflower.myrecipe.view.component.recipeListScreenCompo.RecipeGrid
import com.loyalflower.myrecipe.view.component.recipeListScreenCompo.RecipeListScreenTitleBar
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont
import com.loyalflower.myrecipe.view.theme.WhisperColor
import com.loyalflower.myrecipe.viewmodel.RecipeListViewModel

@Composable
fun RecipeListScreen(
    context: Context,
    finish: () -> Unit,
    goWrite: (Boolean, String, String, List<String>, List<String>, List<String>, Int) -> Unit,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    //deleteMode: 삭제 모드를 위한 변수, isCategoryClicked: 카테고리 클릭 여부를 위한 변수
    //favoriteFilter: 즐겨찾기 분류를 위한 변수, uniqueCategories: 카테고리(중복X)를 위한  변수
    //recipesByCategory: 카테고리별 레시피를 위한 변수
    val deleteMode by viewModel.deleteMode.collectAsStateWithLifecycle()
    val isCategoryClicked by viewModel.isClicked.collectAsStateWithLifecycle()
    val favoriteFilter by viewModel.favoriteFilter.collectAsStateWithLifecycle()
    val uniqueCategories by viewModel.uniqueCategories.collectAsStateWithLifecycle()
    val recipesByCategory by viewModel.recipesByCategory.collectAsStateWithLifecycle()

    //Box형 UI or Bar형 UI
    var isBox by remember { mutableStateOf(true) }

    //뒤로가기 제어(삭제 모드, 카테고리 클릭에 따른 상황별)
    AppBackHandler(
        context = context,
        finish = { finish() },
        deleteMode = deleteMode,
        isCategoryClicked = isCategoryClicked,
        viewModel = viewModel)

    Column {
        RecipeListScreenTitleBar(
            favoriteFilter = favoriteFilter,
            onFavoriteToggle = { viewModel.toggleFavorite() },
            isCategoryClicked = isCategoryClicked,
            deleteMode = deleteMode
        )
        HorizontalDivider(color = Color.LightGray.copy(0.3f))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(WhisperColor)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerMedium))
                //UI 변경 스위치
                BoxOrBar(
                    isAlbum = isBox,
                    onAlbumClick = { isBox = true },
                    onListClick = { isBox = false })
                Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerNormal))
                if (uniqueCategories.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "레시피가 없습니다",
                            style = TextStyle(
                                fontSize = MyRecipeTheme.dimens.textSizeNormal,
                                color = Color.LightGray,
                                fontFamily = PretendardFont,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                } else {
                    //변경될 때 부드러운 애니메이션 / 레시피 리스트 UI 부분
                    AnimatedContent(
                        targetState = isCategoryClicked,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(600)) togetherWith
                                    fadeOut(animationSpec = tween(600))
                        }, label = ""
                    ) { target ->
                        if (target) {
                            RecipeGrid(
                                recipesByCategory = recipesByCategory,
                                isAlbum = isBox,
                                deleteMode = deleteMode,
                                onClick = { recipe ->
                                    goWrite(
                                        false,
                                        recipe.foodName,
                                        recipe.imageUri!!,
                                        recipe.categories,
                                        recipe.ingredients,
                                        recipe.instructions,
                                        recipe.id
                                    )
                                })
                        } else {
                            RecipeGrid(
                                uniqueCategories = uniqueCategories,
                                isAlbum = isBox,
                                onClick = { viewModel.getRecipesByCategory(it) })
                        }
                    }
                }
            }
            //레시피 페이지로 쓰기 모드로 간다.
            FloatingActionButton(
                //isWriting, foodName, imageUri, categories, ingredients, instructions, Id
                onClick = {
                    goWrite(
                        true,
                        "무제",
                        "이미지",
                        listOf("분류"),
                        listOf("재료"),
                        listOf("조리법"),
                        0
                    )
                },
                shape = CircleShape,
                containerColor = BurnOrange,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(MyRecipeTheme.dimens.paddingMedium)
                    .size(MyRecipeTheme.dimens.fabButtonSize)
            ) {
                Icon(imageVector = Icons.Default.Create, contentDescription = "레시피 작성",
                    modifier = Modifier.size(MyRecipeTheme.dimens.iconSizeSmall))
            }
        }
    }
}