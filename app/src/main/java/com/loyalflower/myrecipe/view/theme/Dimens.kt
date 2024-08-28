package com.loyalflower.myrecipe.view.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//핸드폰과 태블릿용 UI 규격을 나눠서 UI 디자인
//text 규격은 Type에서 선언하는게 좋지만 태블릿과 핸드폰의 글자 크기 차이를 두고 싶었다...
data class Dimens(
    val boxButtonHeight: Dp = 56.dp,
    val iconSizeVerySmall: Dp = 16.dp,
    val iconSizeSmall: Dp = 24.dp,
    val iconSizeNormal: Dp = 36.dp,
    val paddingSmall: Dp = 4.dp,
    val paddingNormal: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge:Dp = 40.dp,
    val spacerSmall: Dp = 4.dp,
    val spacerNormal: Dp = 8.dp,
    val spacerMedium: Dp = 16.dp,
    val spacerSemiLarge: Dp = 24.dp,
    val spacerLarge: Dp = 40.dp,
    val titleSize:TextUnit = 20.sp,
    val gridItemSpace2:Dp = 20.dp,
    val textSizeMedium:TextUnit = 12.sp,
    val textSizeNormal:TextUnit = 16.sp,
    val itemBoxSize:Dp = 160.dp,
    val gridItemSpace:Dp = 24.dp,
    val itemBarHeight:Dp = 48.dp,
    val listHeight:Dp = 48.dp,
    val navigationBarHeight:Dp = 64.dp,
    val navigationBarItemText:TextUnit = 12.sp,
    val navigationBarItemSpace:Dp = 4.dp,
    val textPlusIcon:TextUnit = 32.sp,
    val textMinusIcon:TextUnit = 36.sp,
    val sectionHeaderHeight:Dp = 40.dp,
    val thumbnailHeight:Dp = 100.dp,
    val thumbnailWidth:Dp = 160.dp,
    val bigThumbnailHeight:Dp = 250.dp,
    val bigThumbnailWidth:Dp = 400.dp,
    val fabButtonSize:Dp = 56.dp,
    val topBarHeight:Dp = 64.dp
)

val DefaultDimens = Dimens()

val TabletDimens = Dimens(
    boxButtonHeight = 64.dp,
    iconSizeVerySmall = 24.dp,
    iconSizeSmall = 36.dp,
    iconSizeNormal = 48.dp,
    paddingSmall = 8.dp,
    paddingNormal = 16.dp,
    paddingMedium = 24.dp,
    paddingLarge = 56.dp,
    spacerSmall = 8.dp,
    spacerNormal = 16.dp,
    spacerMedium = 24.dp,
    spacerSemiLarge = 32.dp,
    spacerLarge = 56.dp,
    titleSize = 24.sp,
    gridItemSpace2 = 28.dp,
    textSizeMedium = 16.sp,
    textSizeNormal = 20.sp,
    itemBoxSize = 240.dp,
    gridItemSpace = 32.dp,
    itemBarHeight = 80.dp,
    listHeight = 80.dp,
    navigationBarHeight = 80.dp,
    navigationBarItemText = 14.sp,
    navigationBarItemSpace = 8.dp,
    textPlusIcon = 40.sp,
    textMinusIcon = 44.sp,
    sectionHeaderHeight = 56.dp,
    thumbnailHeight = 200.dp,
    thumbnailWidth = 280.dp,
    bigThumbnailHeight = 400.dp,
    bigThumbnailWidth = 640.dp,
    fabButtonSize = 72.dp,
    topBarHeight = 80.dp

)