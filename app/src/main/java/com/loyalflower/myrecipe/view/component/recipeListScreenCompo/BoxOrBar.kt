package com.loyalflower.myrecipe.view.component.recipeListScreenCompo

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.loyalflower.myrecipe.R

@Composable
fun ColumnScope.BoxOrBar(isAlbum: Boolean, onAlbumClick: () -> Unit, onListClick: () -> Unit) {
    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        IconButton(onClick = onAlbumClick) {
            Icon(
                painter = if (isAlbum) painterResource(id = R.drawable.hard_photolib_icon) else painterResource(id = R.drawable.photolib_icon),
                contentDescription = "사진형",
                Modifier.size(MyRecipeTheme.dimens.iconSizeSmall)
            )
        }
        VerticalDivider(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(MyRecipeTheme.dimens.iconSizeSmall),
            thickness = 1.dp,
            color = Color.LightGray.copy(0.5f)
        )
        IconButton(onClick = onListClick) {
            Icon(
                painter = if (isAlbum) painterResource(id = R.drawable.viewlist_icon) else painterResource(id = R.drawable.hard_viewlist_icon),
                contentDescription = "리스트형",
                Modifier.size(MyRecipeTheme.dimens.iconSizeSmall)
            )
        }
    }
}