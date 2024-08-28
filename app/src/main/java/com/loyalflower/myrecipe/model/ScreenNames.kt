package com.loyalflower.myrecipe.model

import kotlinx.serialization.Serializable


@Serializable
object 시작화면

@Serializable
object 홈화면

@Serializable
data class 레시피화면(
    val isWriting:Boolean,
    val foodName:String,
    val imageUri:String,
    val categories:List<String>,
    val ingredients:List<String>,
    val instructions:List<String>,
    val id:Int
)
