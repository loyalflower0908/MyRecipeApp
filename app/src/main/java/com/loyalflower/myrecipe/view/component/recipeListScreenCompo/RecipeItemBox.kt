package com.loyalflower.myrecipe.view.component.recipeListScreenCompo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.loyalflower.myrecipe.R
import com.loyalflower.myrecipe.model.data.recipe.RecipeEntity
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont
import com.loyalflower.myrecipe.view.theme.SpanishGray
import com.loyalflower.myrecipe.viewmodel.RecipeListViewModel

@Composable
fun RecipeItemBox(
    recipe: RecipeEntity,
    deleteMode: Boolean,
    onClick: (RecipeEntity) -> Unit = {},
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    var isSelect by remember { mutableStateOf(false) }
    //delete 모드가 꺼질 때 선택 초기화
    LaunchedEffect(deleteMode) {
        if (!deleteMode) {
            isSelect = false
            viewModel.removeRecipeToDelete(recipe)
        }
    }
    //길게 눌러서 삭제 모드로 들어간다.
    //삭제 모드일땐 터치시 해당 레시피를 선택해 뷰모델의 삭제용 임시 리스트에 저장한다.(다시 터치해서 선택 해제 가능)
    //삭제 모드가 아닐땐 터치시 레시피 상세 페이지로 들어간다
    Box(
        modifier = Modifier
            .size(MyRecipeTheme.dimens.itemBoxSize)
            .clip(RoundedCornerShape(20))
            .pointerInput(deleteMode) {
                detectTapGestures(
                    onLongPress = {
                        viewModel.toggleDeleteMode()
                        if (!isSelect) viewModel.addRecipeToDelete(recipe)
                        isSelect = true
                    },
                    onTap = {
                        if (deleteMode) {
                            if (isSelect) viewModel.removeRecipeToDelete(recipe) else viewModel.addRecipeToDelete(
                                recipe
                            )
                            isSelect = !isSelect
                        } else onClick(recipe)
                    }
                )
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = recipe.imageUri!!),
            contentDescription = "category1",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        RecipeTitle(foodName = recipe.foodName)
        if (deleteMode) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(0.5f))
            )
            SelectionIndicator(isSelect = isSelect)
        } else FavoriteIcon(recipe = recipe, viewModel = viewModel)
    }
}


@Composable
fun RecipeItemBox(categoryName: String, recipeImage: String?, onClick: (String) -> Unit = {}) {
    Box(
        modifier = Modifier
            .size(MyRecipeTheme.dimens.itemBoxSize)
            .clip(RoundedCornerShape(20))
            .clickable { onClick(categoryName) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = recipeImage),
            contentDescription = "category1",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        RecipeTitle(foodName = categoryName)
    }
}

@Composable
private fun BoxScope.RecipeTitle(foodName: String) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth()
            .height(MyRecipeTheme.dimens.boxButtonHeight)
            .background(SpanishGray.copy(0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = foodName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = TextStyle(
                fontFamily = PretendardFont,
                fontSize = MyRecipeTheme.dimens.textSizeNormal,
                color = Color.White,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(4f, 4f),
                    blurRadius = 20f
                )
            ),
            modifier = Modifier.padding(horizontal = MyRecipeTheme.dimens.paddingMedium)
        )
    }
}

@Composable
private fun BoxScope.SelectionIndicator(isSelect: Boolean) {
    Box(
        modifier = Modifier
            .padding(MyRecipeTheme.dimens.paddingMedium)
            .size(MyRecipeTheme.dimens.iconSizeSmall)
            .border(color = Color.Gray, shape = CircleShape, width = 1.dp)
            .align(Alignment.TopEnd)
    ) {
        if (isSelect) {
            Box(
                modifier = Modifier
                    .size(MyRecipeTheme.dimens.iconSizeVerySmall)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun BoxScope.FavoriteIcon(recipe: RecipeEntity, viewModel: RecipeListViewModel) {
    Icon(
        painter = painterResource(id = if (recipe.favorite) R.drawable.fillstar else R.drawable.outlinedstar_icon),
        contentDescription = "Click Star",
        tint = if (recipe.favorite) Color.Yellow else Color.Gray,
        modifier = Modifier
            .padding(MyRecipeTheme.dimens.paddingNormal)
            .size(MyRecipeTheme.dimens.iconSizeNormal)
            .align(Alignment.TopEnd)
            .clickable { viewModel.updateRecipe(recipe.copy(favorite = !recipe.favorite)) }
    )
}