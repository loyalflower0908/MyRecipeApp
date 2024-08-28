package com.loyalflower.myrecipe.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.loyalflower.myrecipe.model.data.recipe.RecipeEntity
import com.loyalflower.myrecipe.model.레시피화면
import com.loyalflower.myrecipe.model.시작화면
import com.loyalflower.myrecipe.model.홈화면
import com.loyalflower.myrecipe.view.screens.HomeScreen
import com.loyalflower.myrecipe.view.screens.SplashScreen
import com.loyalflower.myrecipe.view.screens.WriteRecipeScreen
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.viewmodel.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            MyRecipeTheme(
                //화면 사이즈를 받아 폰인지 태블릿인지 판단.
                windowSize = windowSize.widthSizeClass
            ) {
                //화면 전환을 위한 nav컨트롤러와 레시피 불러오는 과정을 위한 뷰모델 선언
                val navController = rememberNavController()
                val viewModel: RecipeListViewModel = hiltViewModel()

                //메인 화면인 나만의 레시피의 레시피 리스트를 위해 시작할 때 DB의 모든 레시피를 불러옴
                lifecycleScope.launch {
                    viewModel.allRecipes.collect()
                }
                //화면 전환 애니메이션은 각기 다르게 설정하기 위해 None으로 선언.
                //navigation으로 NestedNavigation을 만들려 했으나 현재 TypeSafe 네비게이션에서 매개변수 형식 때문에 개별 설정으로 진행.
                NavHost(
                    navController = navController,
                    startDestination = 시작화면,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    //앱 처음 시작할 때 애니메이션
                    composable<시작화면> {
                        SplashScreen(nextScreen = {
                            navController.navigate(홈화면) {
                                popUpTo(시작화면) {
                                    inclusive = true
                                }
                            }
                        })
                    }
                    //홈 화면
                    composable<홈화면> {
                        HomeScreen(
                            context = applicationContext,
                            finish = { finish() },
                            goWrite = { isWriting, foodName, imageUri, categories, ingredients, instructions, id ->
                                navController.navigate(
                                    route = 레시피화면(
                                        isWriting,
                                        foodName,
                                        imageUri,
                                        categories,
                                        ingredients,
                                        instructions,
                                        id
                                    )
                                )
                            })
                    }
                    //레시피 화면(레시피 작성, 레시피 편집, 확인을 담당한다.
                    //편집 확인을 위해 화면 전환시 매개 변수를 전달 받는다.
                    composable<레시피화면> {
                        val args = it.toRoute<레시피화면>()
                        WriteRecipeScreen(
                            navController = navController,
                            context = LocalContext.current,
                            isWriting = args.isWriting,
                            recipe = RecipeEntity(
                                id = args.id,
                                foodName = args.foodName,
                                imageUri = args.imageUri,
                                categories = args.categories,
                                ingredients = args.ingredients,
                                instructions = args.instructions
                            )
                        )
                    }
                }
            }
        }
    }
}
