package com.loyalflower.myrecipe.view.component.shoppingListScreenCompo

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.loyalflower.myrecipe.model.data.shoppingList.ShoppingItem
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.viewmodel.ShoppingListViewModel


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShoppingListSection(
    shoppingList: List<ShoppingItem>,
    viewModel: ShoppingListViewModel
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = MyRecipeTheme.dimens.paddingMedium)
            .animateContentSize()
    ) {
        items(shoppingList, key = { it.id }) { item ->
            //UI를 밀어서 삭제하기 위한 SwipeToDismissBox
            val dismissState =
                rememberSwipeToDismissBoxState(positionalThreshold = { totalDistance ->
                    totalDistance / 3
                })

            SwipeToDismissBox(state = dismissState, backgroundContent = {}) {
                ShoppingListItem(
                    item = item,
                    onDelete = { viewModel.deleteShoppingListItem(item) },
                    onCheckedChange = { checked ->
                        viewModel.updateItemStatus(item.id, checked)
                    }
                )
            }
            //미는 UI의 상태 관리
            LaunchedEffect(dismissState.currentValue) {
                if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart || dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd) {
                    viewModel.deleteShoppingListItem(item)
                }
            }
        }
    }
}
