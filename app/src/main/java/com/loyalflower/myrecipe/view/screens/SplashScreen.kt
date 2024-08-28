package com.loyalflower.myrecipe.view.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loyalflower.myrecipe.R
import com.loyalflower.myrecipe.viewmodel.StartViewModel

@Composable
fun SplashScreen(viewModel: StartViewModel = hiltViewModel(), nextScreen: () -> Unit) {
    //다음 화면 전환을 위한 트리거
    val toHome by viewModel.toHome
    //애니메이션 시작 트리거
    var startAni by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = toHome) {
        startAni = true
        if (toHome) {
            nextScreen()
        }
    }
    //애니메이션 UI 코드. 불 이미지를 세 파트로 나누어 애니메이션을 진행, delay를 이용해서 순서를 정하고 duration을 동일하게 해서 통일감을 주었다.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(600.dp)//고정 사이즈.
                .align(Alignment.Center)
        ) {
            //고기 이미지
            Image(
                painter = painterResource(id = R.drawable.my_recipe_meat),
                contentDescription = "Meat",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
            //불 왼쪽
            AnimatedVisibility(
                visible = startAni,
                enter = fadeIn(
                    tween(
                        easing = FastOutSlowInEasing,
                        delayMillis = 0,
                        durationMillis = 700
                    )
                ) + scaleIn(
                    tween(easing = FastOutSlowInEasing, delayMillis = 0, durationMillis = 700),
                    transformOrigin = TransformOrigin(0.5f, 0.7f)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.my_recipe_fire1),
                    contentDescription = "Fire1",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            //불 가운데
            AnimatedVisibility(
                visible = startAni,
                enter = fadeIn(
                    tween(
                        easing = FastOutSlowInEasing,
                        delayMillis = 400,
                        durationMillis = 700
                    )
                ) + scaleIn(
                    tween(easing = FastOutSlowInEasing, delayMillis = 400, durationMillis = 700),
                    transformOrigin = TransformOrigin(0.5f, 0.7f)
                )
            ){
                Image(
                    painter = painterResource(id = R.drawable.my_recipe_fire2),
                    contentDescription = "Fire2",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            //불 오른쪽
            AnimatedVisibility(
                visible = startAni,
                enter = fadeIn(
                    tween(
                        easing = FastOutSlowInEasing,
                        delayMillis = 800,
                        durationMillis = 700
                    )
                ) + scaleIn(
                    tween(easing = FastOutSlowInEasing, delayMillis = 800, durationMillis = 700),
                    transformOrigin = TransformOrigin(0.6f, 0.7f)
                )
            ){
                Image(
                    painter = painterResource(id = R.drawable.my_recipe_fire3),
                    contentDescription = "Fire3",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
