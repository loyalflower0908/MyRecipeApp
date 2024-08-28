package com.loyalflower.myrecipe.view.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loyalflower.myrecipe.model.NavigationBarItemModel as NaviBarItem
import com.loyalflower.myrecipe.view.component.CustomNavigationBar
import com.loyalflower.myrecipe.view.component.NestedNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    context: Context,
    finish: () -> Unit,
    //isWriting, foodName, imageUri, categories, ingredients, instructions, id
    goWrite: (Boolean, String, String, List<String>, List<String>, List<String>, Int) -> Unit
) {
    //홈 스크린 진입 애니메이션을 위한 트리거
    var visible by remember { mutableStateOf(false) }
    //네비게이션 바 상태를 위한 변수
    var navigationBarState by remember { mutableStateOf(NaviBarItem.MYRECIPE) }

    LaunchedEffect(Unit) {
        visible = true
    }

    // 홈 스크린 진입 애니메이션
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(700)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500)) + slideOutHorizontally()
    ) {
        Column(modifier = Modifier.background(Color.White)) {
            //네비게이션 바를 통한 화면 전환을 NavHost를 하나 더 사용해서 진행한다.(네비게이션 바를 제외한 부분이 화면 전환 된다)
            NestedNavHost(
                context = context,
                finish = finish,
                goWrite = goWrite,
                navigationBarState = navigationBarState
            )
            //네비게이션 바를 커스텀하기 위해 만든 함수
            CustomNavigationBar(
                selectedItem = navigationBarState,
                onItemSelected = { newState -> navigationBarState = newState })
        }
    }
}


