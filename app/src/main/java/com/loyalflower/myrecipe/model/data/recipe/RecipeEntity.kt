package com.loyalflower.myrecipe.model.data.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val foodName: String,
    val categories: List<String>,
    val ingredients: List<String>,
    val instructions: List<String>,
    val categoriesText: String = categories.toString(),
    val favorite:Boolean = false,
    val imageUri: String? // URI는 문자열로 저장
)
//문자열 리스트를 Room에서 제어하기 위해 TypeConverter로 String으로 제어하기
class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}