package com.loyalflower.myrecipe.view.component.shoppingListScreenCompo

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo.RecipeTextField
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont

@Composable
fun AddShoppingListDialog(
    //showDialog - 다이어로그 트리거, onDismiss - 다이어로그 닫기, onAddItem - 아이템 추가
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAddItem: (String) -> Unit
){
    var newItemText by remember { mutableStateOf("") }

    if (showDialog) {
        //살 것 추가를 위한 다이어로그
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    "살 것 추가",
                    style = TextStyle(
                        fontFamily = PretendardFont,
                        fontSize = MyRecipeTheme.dimens.titleSize,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                )
            },
            text = {
                RecipeTextField(
                    value = newItemText,
                    onValueChange = { newText -> newItemText = newText },
                    label = "새로운 살 것",
                    done = true,
                    enable = true,
                    modifier = Modifier.border(
                        width = 3.dp,
                        color = BurnOrange,
                        shape = RoundedCornerShape(20)
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newItemText.isNotBlank()) {
                            onAddItem(newItemText)
                            newItemText = ""
                        }
                        onDismiss()
                    }
                ) {
                    Text(
                        "추가", color = Color.White,
                        style = TextStyle(
                            fontFamily = PretendardFont,
                            fontSize = MyRecipeTheme.dimens.textSizeNormal,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text(
                        "취소",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = PretendardFont,
                            fontSize = MyRecipeTheme.dimens.textSizeNormal,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            },
            containerColor = Color.White,
            titleContentColor = Color.Black,
            textContentColor = Color.Black
        )
    }
}