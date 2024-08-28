package com.loyalflower.myrecipe.view.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.loyalflower.myrecipe.R
import com.loyalflower.myrecipe.model.NaviBarItemData
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.model.NavigationBarItemModel as NaviBarItem

@Composable
fun CustomNavigationBar(selectedItem: NaviBarItem, onItemSelected: (NaviBarItem) -> Unit){
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.height(MyRecipeTheme.dimens.navigationBarHeight)
    ){
        //네비게이션 아이템을 위한 값들
        val items = listOf(
            NaviBarItemData(NaviBarItem.MYRECIPE, R.drawable.recipe_icon, "나만의 레시피"),
            NaviBarItemData(NaviBarItem.TODAYCHECKLIST, R.drawable.checklist_icon, "오늘 살 것"),
            NaviBarItemData(NaviBarItem.SEARCHRECIPE, R.drawable.recipesearch_icon, "레시피 찾기"),
            NaviBarItemData(NaviBarItem.REPOSITORY, R.drawable.repo_icon, "영상 보관함")
        )

        items.forEach { itemData ->
            CustomNavigationBarItem(
                selected = selectedItem == itemData.item,
                onClick = { onItemSelected(itemData.item) },
                iconPainter = painterResource(id = itemData.iconRes),
                label = itemData.label
            )
        }
    }
}