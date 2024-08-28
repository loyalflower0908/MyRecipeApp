package com.loyalflower.myrecipe.viewmodel

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loyalflower.myrecipe.model.data.recipe.RecipeEntity
import com.loyalflower.myrecipe.model.data.recipe.RecipeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.FileNotFoundException

import javax.inject.Inject

@HiltViewModel
class RecipePageViewModel @Inject constructor(private val repository: RecipeRepositoryImpl):ViewModel() {

    //쓰기 모드인 지 파악
    private val _writingMode = MutableStateFlow(true)
    val writingMode: StateFlow<Boolean> = _writingMode.asStateFlow()

    //현재 화면 전환 중인지 파악
    private val _isTransitioning = MutableStateFlow(false)
    val isTransitioning: StateFlow<Boolean> = _isTransitioning.asStateFlow()

    // 음식 이름, 분류, 재료, 조리 순서, id, uri 상태
    var foodName = mutableStateOf("")
        private set

    var categoryText = mutableStateOf("")
        private set

    private var _categories = mutableStateListOf<String>()
    val categories: SnapshotStateList<String> get() = _categories

    private var _ingredients = mutableStateListOf("")
    val ingredients: SnapshotStateList<String> get() = _ingredients

    private var _instructions = mutableStateListOf("")
    val instructions: SnapshotStateList<String> get() = _instructions

    private var id = mutableIntStateOf(0)

    private var _imageUri = mutableStateOf<Uri?>(null)
    val imageUri: State<Uri?> = _imageUri

    fun uriToBitmap(uri: Uri, context: Context): Bitmap? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    //사진 선택시 Uri 입력 값 변경
    fun onImageSelected(uri: Uri?) {
        _imageUri.value = uri
    }

    //사진 권한 요청
    fun takePersistableUriPermission(context: Context, uri: Uri) {
        val contentResolver = context.contentResolver
        val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        contentResolver.takePersistableUriPermission(uri, takeFlags)
    }

    // 카테고리 추가 로직
    private fun addCategory(newCategory: String) {
        val trimmedText = newCategory.trim()
        if (trimmedText.isNotEmpty() && !_categories.contains(trimmedText)) {
            _categories.add(trimmedText)
        }
    }

    // 재료 추가 로직
    fun addIngredient() {
        _ingredients.add("")
    }

    // 조리 순서 추가 로직
    fun addInstruction() {
        _instructions.add("")
    }

    // 음식 이름, 분류 입력 값 변경
    fun onFoodNameChange(newName: String) {
        foodName.value = newName
    }

    //카테고리 텍스트 입력 값 변경 및 ","를 인식해서 카테고리 리스트에 주가
    fun onCategoryTextChange(newText: String) {
        if (newText.contains(",")) {
            addCategory(newText.substringBefore(","))
            categoryText.value = ""
        } else {
            categoryText.value = newText
        }
    }

    // 재료 입력 값 변경
    fun onIngredientChange(index: Int, newIngredient: String) {
        _ingredients[index] = newIngredient
    }

    //재료 삭제
    fun deleteIngredient() {
        _ingredients.removeLast()
    }


    // 조리 순서 입력 값 변경
    fun onInstructionChange(index: Int, newInstruction: String) {
        _instructions[index] = newInstruction
    }

    //조리 순서 삭제
    fun deleteInstruction(){
        _instructions.removeLast()
    }

    //레시피 저장
    fun saveRecipe(){
        val recipe: RecipeEntity
        if(id.intValue != 0){
            recipe = RecipeEntity(
                id = id.intValue,
                foodName = foodName.value,
                categories = categories.toList(),
                ingredients = ingredients.toList(),
                instructions = instructions.toList(),
                imageUri = imageUri.value.toString()
            )
        }else{
            recipe = RecipeEntity(
                foodName = foodName.value,
                categories = categories.toList(),
                ingredients = ingredients.toList(),
                instructions = instructions.toList(),
                imageUri = imageUri.value.toString()
            )
        }
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    //레시피 읽어오기
    fun readRecipe(recipe: RecipeEntity){
        foodName.value = recipe.foodName
        categoryText.value = ""
        _categories = recipe.categories.toMutableStateList()
        _ingredients = recipe.ingredients.toMutableStateList()
        _instructions = recipe.instructions.toMutableStateList()
        _imageUri.value = Uri.parse(recipe.imageUri)
        id.intValue = recipe.id
    }

    fun isTransitioningOn() {
        _isTransitioning.value = true
    }

    //쓰기 모드 설정
    fun isWritingSet(boolean: Boolean){
        _writingMode.value = boolean
    }
}