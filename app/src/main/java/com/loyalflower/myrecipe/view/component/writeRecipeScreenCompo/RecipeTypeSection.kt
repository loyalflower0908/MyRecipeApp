package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.viewmodel.RecipePageViewModel

@Composable
fun RecipeTypeSection(
    viewModel: RecipePageViewModel,
    categories: SnapshotStateList<String>,
    writingMode:Boolean
) {
    SectionHeader(title = "음식 분류")
    if(writingMode){
        RecipeTextField(
            value = viewModel.categoryText.value,
            onValueChange = viewModel::onCategoryTextChange,
            label = "분류 입력 (콤마로 구분)",
            done = false,
            enable = true
        )
    }
    //분류를 인풋칩으로 보여줌
    LazyRow(
        modifier = Modifier
            .padding(start = MyRecipeTheme.dimens.paddingNormal)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MyRecipeTheme.dimens.spacerNormal)
    ) {
        items(categories) { category ->
            InputChip(
                selected = false,
                onClick = {
                    if(writingMode) categories.remove(category)
                },
                label = { Text(category) },
                trailingIcon = {
                    if(writingMode) Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete"
                    )
                },
                colors = InputChipDefaults.inputChipColors(
                    containerColor = Color(
                        0xFFFFF8E1
                    ), trailingIconColor = Color.Black, labelColor = Color.Black
                )
            )
        }
    }
}