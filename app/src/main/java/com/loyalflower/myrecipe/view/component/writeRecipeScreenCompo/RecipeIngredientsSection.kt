package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.viewmodel.RecipePageViewModel


@Composable
fun ColumnScope.RecipeIngredientsSection(
    ingredients: SnapshotStateList<String>,
    viewModel: RecipePageViewModel,
    writingMode: Boolean
) {
    SectionHeader(title = "재료 목록")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        ingredients.forEachIndexed { index, ingredient ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.width(MyRecipeTheme.dimens.spacerMedium))
                Box(
                    modifier = Modifier
                        .size(width = 12.dp, height = 4.dp)
                        .background(
                            BurnOrange
                        )
                )
                RecipeTextField(
                    value = ingredient,
                    onValueChange = { viewModel.onIngredientChange(index, it) },
                    label = "재료 입력 : ",
                    done = false,
                    enable = writingMode
                )
            }
        }
    }
    if (writingMode) {
        AddMinusButtons(
            addClick = { viewModel.addIngredient() },
            minusClick = { viewModel.deleteIngredient() },
            items = ingredients
        )
    }
    Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerNormal))
}