package com.loyalflower.myrecipe.view.component

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.loyalflower.myrecipe.viewmodel.RecipeListViewModel

@Composable
fun AppBackHandler(
    context: Context,
    finish: () -> Unit,
    deleteMode: Boolean,
    isCategoryClicked: Boolean,
    viewModel: RecipeListViewModel
) {
    var waitTime by remember { mutableLongStateOf(0L) }
    BackHandler(enabled = true, onBack = {
        if(isCategoryClicked){
            if (deleteMode) viewModel.toggleDeleteMode() else viewModel.toggleIsClicked()
        }else{
            if (System.currentTimeMillis() - waitTime >= 1600) {
                waitTime = System.currentTimeMillis()
                Toast.makeText(context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                finish()
            }
        }
    })
}

@Composable
fun AppBackHandler(
    context: Context,
    finish: () -> Unit
) {
    var waitTime by remember { mutableLongStateOf(0L) }
    BackHandler(enabled = true, onBack = {
        if (System.currentTimeMillis() - waitTime >= 1600) {
            waitTime = System.currentTimeMillis()
            Toast.makeText(context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT)
                .show()
        } else {
            finish()
        }
    })
}