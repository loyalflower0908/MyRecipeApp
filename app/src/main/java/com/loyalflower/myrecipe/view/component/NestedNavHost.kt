package com.loyalflower.myrecipe.view.component

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loyalflower.myrecipe.model.NavigationBarItemModel as NaviBarItem
import com.loyalflower.myrecipe.model.레시피목록화면
import com.loyalflower.myrecipe.model.레시피보관함화면
import com.loyalflower.myrecipe.model.레시피플랫폼검색화면
import com.loyalflower.myrecipe.model.오늘살것화면
import com.loyalflower.myrecipe.view.nestedScreen.RecipeListScreen
import com.loyalflower.myrecipe.view.nestedScreen.RecipeRepositoryScreen
import com.loyalflower.myrecipe.view.nestedScreen.SearchRecipeScreen
import com.loyalflower.myrecipe.view.nestedScreen.ShoppingListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ColumnScope.NestedNavHost(
    context: Context,
    finish: () -> Unit,
    goWrite: (Boolean, String, String, List<String>, List<String>, List<String>, Int) -> Unit,
    navigationBarState: NaviBarItem
) {
    //중첩 네비게이션을 위한 컨트롤러
    val nestedNavController = rememberNavController()
    //이전 스택을 날리기 위해 상태 저장을 위한 변수
    var beforeNavigationState: NaviBarItem? by remember {
        mutableStateOf(null)
    }
    //현재 상태 저장
    LaunchedEffect(Unit) {
        beforeNavigationState = navigationBarState
    }
    //네비게이션 바 상태가 변할 시 이전 상태 페이지는 날리고 다음 페이지로
    LaunchedEffect(navigationBarState) {
        nestedNavController.navigate(naviBarStateToRoute(navigationBarState)) {
            popUpTo(
                naviBarStateToRoute(beforeNavigationState!!)
            ) { inclusive = true }
        }
        beforeNavigationState = navigationBarState
    }
    //화면 전환 시 옆으로 넘어가는 애니메이션을 줌
    NavHost(
        navController = nestedNavController,
        startDestination = 레시피목록화면,
        enterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 1000)) + slideInHorizontally(
                tween(500),
                initialOffsetX = { it })
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 1000)) + slideOutHorizontally(
                tween(500),
                targetOffsetX = { -it })
        },
        modifier = Modifier.weight(1f)
    ) {
        composable<레시피목록화면> {
            RecipeListScreen(
                context = context,
                finish = finish,
                goWrite = goWrite
            )
        }
        composable<오늘살것화면> {
            ShoppingListScreen(context = context, finish = finish)
        }
        composable<레시피플랫폼검색화면> {
            SearchRecipeScreen(context = context, finish = finish)
        }
        composable<레시피보관함화면> {
            RecipeRepositoryScreen(context = context, finish = finish)
        }
    }
}

//네비게이션 상태에 따른 경로
private fun naviBarStateToRoute(naviBarState: NaviBarItem): Any {
    return when (naviBarState) {
        NaviBarItem.MYRECIPE -> 레시피목록화면
        NaviBarItem.TODAYCHECKLIST -> 오늘살것화면
        NaviBarItem.SEARCHRECIPE -> 레시피플랫폼검색화면
        NaviBarItem.REPOSITORY -> 레시피보관함화면
    }
}