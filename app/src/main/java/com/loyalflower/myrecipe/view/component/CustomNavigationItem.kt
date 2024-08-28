package com.loyalflower.myrecipe.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont

@Composable
fun RowScope.CustomNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    iconPainter: Painter,
    label: String
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MyRecipeTheme.dimens.navigationBarItemSpace)
            ) {
                Icon(
                    painter = iconPainter,
                    contentDescription = label,
                    Modifier.size(MyRecipeTheme.dimens.iconSizeSmall)
                )
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = MyRecipeTheme.dimens.navigationBarItemText,
                        fontFamily = PretendardFont,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

        },
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = BurnOrange,
            selectedIconColor = BurnOrange,
            indicatorColor = Color.Transparent
        )
    )
}