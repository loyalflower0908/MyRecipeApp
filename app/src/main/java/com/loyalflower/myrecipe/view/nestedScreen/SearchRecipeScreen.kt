package com.loyalflower.myrecipe.view.nestedScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loyalflower.myrecipe.view.component.AppBackHandler
import com.loyalflower.myrecipe.view.component.TopBar
import com.loyalflower.myrecipe.view.component.searchRecipeScreenCompo.RecipeList
import com.loyalflower.myrecipe.view.component.searchRecipeScreenCompo.SearchBar
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.WhisperColor
import com.loyalflower.myrecipe.viewmodel.RecipeSearchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchRecipeScreen(
    context: Context,
    finish: () -> Unit,
    viewModel: RecipeSearchViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    //키보드 Focus를 위한 변수들
    val focusManager = LocalFocusManager.current
    val searchBarFocusRequester = remember { FocusRequester() }

    //뒤로가기 버튼 관리
    AppBackHandler(context = context, finish = finish)

    Column(modifier = Modifier.background(Color.White)) {
        TopBar(title = "레시피 영상 검색")
        Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerMedium))
        //검색 바
        SearchBar(
            modifier = Modifier
                .padding(horizontal = MyRecipeTheme.dimens.paddingLarge)
                .fillMaxWidth()
                .focusRequester(searchBarFocusRequester),
            onCancle = { viewModel.resetRecipes() },
            onSearch = { query ->
                viewModel.searchRecipes(query)
                focusManager.clearFocus()
            })
        Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerMedium))
        HorizontalDivider(color = Color.LightGray.copy(0.3f))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(WhisperColor)
        ) {
            if (isLoading) {
                //로딩 중일때 UI
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                //검색 결과, 클릭시 관련 앱으로 연결
                RecipeList(recipes, viewModel) { recipe ->
                    val url = "https://www.youtube.com/watch?v=${recipe.id}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                    context.startActivity(intent)
                }
            }
        }
    }

    // 화면이 처음 렌더링될 때 SearchBar에 포커스를 요청
    LaunchedEffect(Unit) {
        searchBarFocusRequester.requestFocus()
    }

}
