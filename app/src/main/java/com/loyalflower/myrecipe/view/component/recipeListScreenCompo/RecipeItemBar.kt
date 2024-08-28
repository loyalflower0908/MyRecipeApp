package com.loyalflower.myrecipe.view.component.recipeListScreenCompo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.loyalflower.myrecipe.model.data.recipe.RecipeEntity
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont
import com.loyalflower.myrecipe.view.theme.SpanishGray
import com.loyalflower.myrecipe.viewmodel.RecipeListViewModel

@Composable
fun RecipeItemBar(
    recipe: RecipeEntity,
    deleteMode: Boolean,
    onClick: (RecipeEntity) -> Unit = {},
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    var isSelect by remember { mutableStateOf(false) }

    //delete 모드가 꺼질 때 선택 초기화
    LaunchedEffect(deleteMode) {
        if (!deleteMode) {
            isSelect = false // deleteMode가 꺼질 때 선택을 초기화
            viewModel.removeRecipeToDelete(recipe)
        }
    }
    //길게 눌러서 삭제 모드로 들어간다.
    //삭제 모드일땐 터치시 해당 레시피를 선택해 뷰모델의 삭제용 임시 리스트에 저장한다.(다시 터치해서 선택 해제 가능)
    //삭제 모드가 아닐땐 터치시 레시피 상세 페이지로 들어간다
    Box(
        modifier = Modifier
            .size(
                width = MyRecipeTheme.dimens.itemBoxSize,
                height = MyRecipeTheme.dimens.itemBarHeight
            )
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(SpanishGray.copy(0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = recipe.foodName,
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
                )
            )
        }
        if (deleteMode) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if(isSelect) Color.Red.copy(0.3f) else Color.Gray.copy(0.5f))
            )
        }
    }
}


@Composable
fun RecipeItemBar(categoryName: String, recipeImage: String?, onClick: (String) -> Unit = {}) {
    Box(
        modifier = Modifier
            .size(
                width = MyRecipeTheme.dimens.itemBoxSize,
                height = MyRecipeTheme.dimens.itemBarHeight
            )
            .clip(RoundedCornerShape(20))
            .clickable { onClick(categoryName) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = recipeImage),
            contentDescription = "category1",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(SpanishGray.copy(0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = categoryName,
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
                )
            )
        }
    }
}
