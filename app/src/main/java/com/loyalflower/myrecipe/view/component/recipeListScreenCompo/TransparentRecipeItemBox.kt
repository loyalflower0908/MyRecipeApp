package com.loyalflower.myrecipe.view.component.recipeListScreenCompo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme

@Composable
fun TransparentRecipeItemBox(){
    Box(
        modifier = Modifier
            .size(MyRecipeTheme.dimens.itemBoxSize)
            .background(Color.Transparent)
    )
}