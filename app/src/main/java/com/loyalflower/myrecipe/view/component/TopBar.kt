package com.loyalflower.myrecipe.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont

@Composable
fun TopBar(title:String, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(MyRecipeTheme.dimens.topBarHeight)
            .background(Color.White)
    ) {
        Text(
            text = title,
            color = BurnOrange,
            style = TextStyle(
                fontFamily = PretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = MyRecipeTheme.dimens.titleSize
            ),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}