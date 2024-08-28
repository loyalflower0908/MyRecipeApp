package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun SectionHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MyRecipeTheme.dimens.sectionHeaderHeight)
            .background(BurnOrange)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontFamily = PretendardFont,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = MyRecipeTheme.dimens.textSizeNormal
            ),
            modifier = Modifier
                .padding(start = MyRecipeTheme.dimens.paddingMedium)
                .align(Alignment.CenterStart)
        )
    }
}