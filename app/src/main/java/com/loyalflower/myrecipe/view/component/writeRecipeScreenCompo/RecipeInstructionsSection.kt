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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont
import com.loyalflower.myrecipe.viewmodel.RecipePageViewModel

@Composable
fun ColumnScope.RecipeInstructionsSection(
    instructions: SnapshotStateList<String>,
    viewModel: RecipePageViewModel,
    writingMode: Boolean
) {
    SectionHeader(title = "조리 순서")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
    ) {
        instructions.forEachIndexed { index, instruction ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Spacer(modifier = Modifier.width(MyRecipeTheme.dimens.spacerMedium))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(BurnOrange)
                ) {
                    Text(
                        "${index + 1}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = PretendardFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                RecipeTextField(
                    value = instruction,
                    onValueChange = { viewModel.onInstructionChange(index, it) },
                    label = "단계 ${index + 1} :",
                    done = instructions.lastIndex == index,
                    enable = writingMode
                )
            }
        }
    }
    if (writingMode) {
        AddMinusButtons(
            addClick = { viewModel.addInstruction() },
            minusClick = { viewModel.deleteInstruction() },
            items = instructions
        )
    }
    Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerNormal))
}