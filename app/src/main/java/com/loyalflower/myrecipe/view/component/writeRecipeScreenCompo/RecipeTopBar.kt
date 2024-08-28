package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont
import com.loyalflower.myrecipe.viewmodel.RecipePageViewModel

@Composable
fun RecipeTopBar(
    isTransitioning: Boolean,
    navController: NavController,
    viewModel: RecipePageViewModel = hiltViewModel(),
    writingMode:Boolean
) {
    //탑 바를 쓰기 상태인지, 편집 상태인지에 따라 변경
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MyRecipeTheme.dimens.topBarHeight)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "뒤로가기",
            modifier = Modifier
                .padding(start = MyRecipeTheme.dimens.paddingMedium)
                .size(MyRecipeTheme.dimens.iconSizeNormal)
                .align(Alignment.CenterStart)
                .clickable(enabled = !isTransitioning) {
                    if (navController.previousBackStackEntry != null) {
                        viewModel.isTransitioningOn()
                        navController.popBackStack()
                    }
                }
        )
        Text(
            text = "레시피 기록하기",
            color = BurnOrange,
            style = TextStyle(
                fontFamily = PretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = MyRecipeTheme.dimens.titleSize
            ),
            modifier = Modifier
                .align(Alignment.Center)
        )
        if(writingMode){
            Text(
                text = "저장",
                color = Color.Black,
                style = TextStyle(
                    fontFamily = PretendardFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = MyRecipeTheme.dimens.textSizeNormal
                ),
                modifier = Modifier
                    .padding(end = MyRecipeTheme.dimens.paddingMedium)
                    .align(Alignment.CenterEnd)
                    .clickable{
                        viewModel.saveRecipe()
                        viewModel.isWritingSet(false)
                    }
            )
        }else{
            Text(
                text = "편집",
                color = Color.Black,
                style = TextStyle(
                    fontFamily = PretendardFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = MyRecipeTheme.dimens.textSizeNormal
                ),
                modifier = Modifier
                    .padding(end = MyRecipeTheme.dimens.paddingMedium)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        viewModel.isWritingSet(true)
                    }
            )
        }
    }
}