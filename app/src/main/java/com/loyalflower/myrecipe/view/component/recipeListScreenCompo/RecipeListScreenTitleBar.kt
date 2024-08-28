package com.loyalflower.myrecipe.view.component.recipeListScreenCompo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyalflower.myrecipe.R
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont
import com.loyalflower.myrecipe.viewmodel.RecipeListViewModel

@Composable
fun RecipeListScreenTitleBar(
    favoriteFilter: Boolean,
    onFavoriteToggle: () -> Unit = {},
    isCategoryClicked: Boolean,
    deleteMode: Boolean,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    //카테고리 선택과 삭제 모드에 따라 UI와 기능 변경
    Box(
        Modifier
            .fillMaxWidth()
            .height(MyRecipeTheme.dimens.topBarHeight)
    ) {
        if (!isCategoryClicked) {
            Text(
                text = "나만의 레시피",
                color = BurnOrange,
                style = TextStyle(
                    fontFamily = PretendardFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MyRecipeTheme.dimens.titleSize
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = MyRecipeTheme.dimens.paddingMedium)
            )
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = MyRecipeTheme.dimens.paddingMedium)
                    .size(MyRecipeTheme.dimens.iconSizeNormal)
                    .clickable { if (deleteMode) viewModel.toggleDeleteMode() else viewModel.toggleIsClicked() }
            )
        }
        if (!deleteMode) {
            Icon(
                painter = painterResource(id = if (favoriteFilter) R.drawable.fillstar else R.drawable.outlinedstar_icon),
                contentDescription = "즐겨찾기",
                modifier = Modifier
                    .padding(end = MyRecipeTheme.dimens.paddingMedium)
                    .size(MyRecipeTheme.dimens.iconSizeNormal)
                    .align(Alignment.CenterEnd)
                    .clickable { onFavoriteToggle() }
            )
        } else {
            Text(
                text = "레시피 삭제",
                color = BurnOrange,
                style = TextStyle(
                    fontFamily = PretendardFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MyRecipeTheme.dimens.titleSize
                ),
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .align(
                        Alignment.CenterEnd
                    )
                    .padding(end = MyRecipeTheme.dimens.paddingMedium)
                    .size(MyRecipeTheme.dimens.iconSizeNormal)
                    .clickable { viewModel.deleteRecipes() },
                tint = Color.Red
            )
        }
    }
}
