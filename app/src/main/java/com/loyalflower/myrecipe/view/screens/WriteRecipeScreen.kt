package com.loyalflower.myrecipe.view.screens

import android.content.Context
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.loyalflower.myrecipe.model.data.recipe.RecipeEntity
import com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo.RecipeImageSection
import com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo.RecipeIngredientsSection
import com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo.RecipeInstructionsSection
import com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo.RecipeNameSection
import com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo.RecipeTypeSection
import com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo.RecipeTopBar
import com.loyalflower.myrecipe.viewmodel.RecipePageViewModel


@Composable
fun WriteRecipeScreen(
    navController: NavController,
    context: Context,
    viewModel: RecipePageViewModel = hiltViewModel(),
    isWriting: Boolean,
    recipe: RecipeEntity
) {
    val writingMode by viewModel.writingMode.collectAsStateWithLifecycle()
    val isTransitioning by viewModel.isTransitioning.collectAsStateWithLifecycle()

    val categories = viewModel.categories
    val ingredients = viewModel.ingredients
    val instructions = viewModel.instructions
    val foodName by remember { viewModel.foodName }
    val imageUri by viewModel.imageUri

    var visible by remember { mutableStateOf(false) }

    //현재 쓰기 모드인지 확인(읽기 모드일시 레시피 정보를 불러와서 채우고 수정이 안되게 함)
    LaunchedEffect(Unit) {
        viewModel.isWritingSet(isWriting)
        if (!isWriting) {
            viewModel.readRecipe(recipe)
        }
    }

    //사진을 가져오기 위한 런쳐, 권한을 요청에서 이후에도 사진을 출력할 수 있게 한다.
    val openDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                // 영구적인 권한을 요청합니다.
                viewModel.takePersistableUriPermission(context, uri)
                viewModel.onImageSelected(uri)
            }
        }
    )
    val bitmap: Bitmap? = imageUri?.let { viewModel.uriToBitmap(it, context) }

    //애니메이션 트리거
    LaunchedEffect(Unit) {
        visible = true
    }
    //슬라이드 애니메이션
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + slideInHorizontally(
            tween(500),
            initialOffsetX = { it }),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            //상단 바.
            RecipeTopBar(
                isTransitioning = isTransitioning,
                navController = navController,
                writingMode = writingMode
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // 0. 완성된 음식 사진 추가
                RecipeImageSection(openDocumentLauncher, imageUri, bitmap, writingMode = writingMode)
                //1.음식 이름 입력
                RecipeNameSection(foodName, viewModel, writingMode = writingMode)
                // 2. 분류 입력
                RecipeTypeSection(viewModel, categories, writingMode = writingMode)
                // 3. 재료 입력
                RecipeIngredientsSection(ingredients, viewModel, writingMode = writingMode)
                // 4. 조리법 입력
                RecipeInstructionsSection(instructions, viewModel, writingMode = writingMode)
            }
        }
    }
}





