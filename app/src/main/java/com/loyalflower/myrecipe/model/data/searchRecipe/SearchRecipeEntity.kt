package com.loyalflower.myrecipe.model.data.searchRecipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_recipe")
data class SearchRecipeEntity(
    @PrimaryKey val id: String,
    val title: String,
    val thumbnailUrl: String,
    val description: String,
    val publishDate: String,
    val channelName: String
)