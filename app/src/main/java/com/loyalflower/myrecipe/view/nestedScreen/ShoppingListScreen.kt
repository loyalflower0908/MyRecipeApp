package com.loyalflower.myrecipe.view.nestedScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loyalflower.myrecipe.view.component.AppBackHandler
import com.loyalflower.myrecipe.view.component.TopBar
import com.loyalflower.myrecipe.view.component.shoppingListScreenCompo.AddShoppingListDialog
import com.loyalflower.myrecipe.view.component.shoppingListScreenCompo.DateSelectSection
import com.loyalflower.myrecipe.view.component.shoppingListScreenCompo.ShoppingListSection
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.WhisperColor
import com.loyalflower.myrecipe.viewmodel.ShoppingListViewModel
import java.util.Date

@Composable
fun ShoppingListScreen(
    context: Context,
    finish: () -> Unit,
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val shoppingList by viewModel.shoppingList.collectAsStateWithLifecycle()
    val showDialog by viewModel.showDialog.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.setDate(Date())
    }

    //뒤로 가기 버튼 관리
    AppBackHandler(context = context, finish = { finish() })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhisperColor)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {
            TopBar(title = "오늘 살 것")
            HorizontalDivider(color = Color.LightGray.copy(0.3f))
            //날짜 선택 파트
            DateSelectSection(selectedDate, viewModel)
            Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerSemiLarge))
            //오늘 살 것 출력 파트
            ShoppingListSection(shoppingList, viewModel)
        }
        //쇼핑 리스트 추가 Dialog On
        FloatingActionButton(
            onClick = { viewModel.setShowDialog(true) },
            shape = CircleShape,
            containerColor = BurnOrange,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(MyRecipeTheme.dimens.paddingMedium)
                .size(MyRecipeTheme.dimens.fabButtonSize)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "살 것 추가",
                modifier = Modifier.size(MyRecipeTheme.dimens.iconSizeSmall)
            )
        }
    }
    //쇼핑 리스트 추가를 위한 Dialog
    AddShoppingListDialog(
        showDialog = showDialog,
        onDismiss = { viewModel.setShowDialog(false) },
        onAddItem = { newItem ->
            viewModel.addItem(newItem)
            viewModel.setShowDialog(false)
        }
    )
}
