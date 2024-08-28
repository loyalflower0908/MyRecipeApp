package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import androidx.compose.runtime.Composable
import com.loyalflower.myrecipe.viewmodel.RecipePageViewModel

@Composable
fun RecipeNameSection(
    foodName: String,
    viewModel: RecipePageViewModel,
    writingMode:Boolean
) {
    SectionHeader(title = "음식 이름")
    RecipeTextField(
        value = foodName,
        onValueChange = viewModel::onFoodNameChange,
        label = "이름: ",
        done = false,
        enable = writingMode
    )
}