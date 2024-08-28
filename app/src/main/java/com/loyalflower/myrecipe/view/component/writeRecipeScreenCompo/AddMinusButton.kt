package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme

@Composable
fun ColumnScope.AddMinusButtons(
    addClick: () -> Unit,
    minusClick: () -> Unit,
    items: SnapshotStateList<String>
) {
    Row(
        Modifier.align(Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { addClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = BurnOrange
            )
        ) {
            Text("+", fontSize = MyRecipeTheme.dimens.textPlusIcon)
        }
        //리스트 사이즈를 체크해서 하나는 남겨놓음
        Button(
            onClick = { minusClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = BurnOrange,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Transparent
            ),
            enabled = items.size > 1
        ) {
            Text("-", fontSize = MyRecipeTheme.dimens.textMinusIcon)
        }
    }
}