package com.loyalflower.myrecipe.view.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

//기본색 BurnOrange
private val DarkColorScheme = darkColorScheme(
    primary = BurnOrange,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = BurnOrange,
    secondary = PurpleGrey40,
    tertiary = Pink40,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val LocalDimens = staticCompositionLocalOf { DefaultDimens }

@Composable
fun ProvideDimens(
    dimens: Dimens,
    content: @Composable () -> Unit
){
    val dimensionSet = remember { dimens }
    CompositionLocalProvider(LocalDimens provides dimensionSet, content = content)
}

@Composable
fun MyRecipeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // 핸드폰에 따라 달라지는 컬러 Off
    dynamicColor: Boolean = false,
    windowSize:WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    //화면 사이즈를 받아 태블릿, 핸드폰 규격 설정 후 테마에 적용
    val dimensions = if(windowSize > WindowWidthSizeClass.Compact)
        TabletDimens
    else
        DefaultDimens

    ProvideDimens(dimens = dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object MyRecipeTheme {
    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}