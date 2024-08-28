package com.loyalflower.myrecipe.view.component.shoppingListScreenCompo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.loyalflower.myrecipe.model.data.shoppingList.ShoppingItem
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onDelete: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    // Row와 관련된 상태를 remember로 최적화
    var isChecked by remember { mutableStateOf(item.isCompleted) }

    // 상태 변경 시 처리
    LaunchedEffect(item.isCompleted) {
        isChecked = item.isCompleted
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(MyRecipeTheme.dimens.listHeight)
            .padding(vertical = MyRecipeTheme.dimens.paddingNormal),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .toggleable(
                    value = isChecked,
                    role = Role.Checkbox,
                    onValueChange = {
                        checked -> onCheckedChange(checked)
                        isChecked = checked
                    }),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = null
            )
            Spacer(modifier = Modifier.width(MyRecipeTheme.dimens.spacerNormal))
            //살 것을 체크했을때 텍스트에 줄긋기
            Text(
                text = item.itemName,
                textDecoration = if (item.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = PretendardFont,
                    fontSize = MyRecipeTheme.dimens.textSizeNormal,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.width(MyRecipeTheme.dimens.spacerNormal))
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            modifier = Modifier.clickable { onDelete() })
    }
}
