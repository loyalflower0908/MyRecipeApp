package com.loyalflower.myrecipe.view.component.recipeListScreenCompo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme

@Composable
fun TransparentRecipeItemBar(){
    Box(
        modifier = Modifier
            .size(
                width = MyRecipeTheme.dimens.itemBoxSize,
                height = MyRecipeTheme.dimens.itemBarHeight
            )
            .background(Color.Transparent)
    )
}