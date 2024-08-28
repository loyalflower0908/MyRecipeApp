package com.loyalflower.myrecipe.view.component.searchRecipeScreenCompo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.loyalflower.myrecipe.R
import com.loyalflower.myrecipe.model.data.searchRecipe.SearchRecipeEntity
import com.loyalflower.myrecipe.model.utils.DateUtils
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecipeItem(
    recipe: SearchRecipeEntity,
    isFavorite: Boolean,
    onClick: (SearchRecipeEntity) -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(MyRecipeTheme.dimens.paddingNormal)
        .clickable { onClick(recipe) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.size(
                    width = MyRecipeTheme.dimens.thumbnailWidth,
                    height = MyRecipeTheme.dimens.thumbnailHeight
                )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(recipe.thumbnailUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                FavoriteIcon(isFavorite = isFavorite, onFavoriteClick = onFavoriteClick)
            }
            Spacer(modifier = Modifier.width(MyRecipeTheme.dimens.spacerNormal))
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = recipe.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontFamily = PretendardFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = MyRecipeTheme.dimens.textSizeNormal
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerSemiLarge))
                Text(
                    text = DateUtils.formatDate(recipe.publishDate),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontFamily = PretendardFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = MyRecipeTheme.dimens.textSizeMedium
                    )
                )
                Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerNormal))
                Text(
                    text = recipe.channelName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontFamily = PretendardFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = MyRecipeTheme.dimens.textSizeMedium
                    )
                )
            }
        }
    }
}

@Composable
private fun BoxScope.FavoriteIcon(
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit
) {
    Icon(
        painter = painterResource(id = if (isFavorite) R.drawable.fillstar else R.drawable.outlinedstar_icon),
        contentDescription = "Click Star",
        tint = Color.White,//if (isRecipeExists) Color.Yellow else Color.Gray,
        modifier = Modifier
            .padding(MyRecipeTheme.dimens.paddingNormal)
            .size(MyRecipeTheme.dimens.iconSizeNormal)
            .align(Alignment.TopStart)
            .clickable {
                onFavoriteClick(isFavorite)
            }
    )
}