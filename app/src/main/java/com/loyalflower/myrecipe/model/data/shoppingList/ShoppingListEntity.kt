package com.loyalflower.myrecipe.model.data.shoppingList

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "shopping_list")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Date, // 날짜
    val items: List<ShoppingItem>, // 아이템 리스트 (각 아이템에 대해 완료 여부 포함)
)

// ShoppingItemList를 저장하기 위해 String으로 바꿔주는 TypeConverter 선언, Date도 마찬가지로 Long으로 전환
class ShoppingListConverters {
    @TypeConverter
    fun fromShoppingItemList(value: List<ShoppingItem>): String {
        return value.joinToString(separator = "|") { "${it.id},${it.itemName},${it.isCompleted}" }
    }

    @TypeConverter
    fun toShoppingItemList(value: String): List<ShoppingItem> {
        if (value.isBlank()) return emptyList()
        return value.split("|").map {
            val parts = it.split(",")
            if (parts.size == 3) {
                ShoppingItem(id = parts[0].toLong(), itemName = parts[1], isCompleted = parts[2].toBoolean())
            } else {
                ShoppingItem(id = 0L, itemName = "", isCompleted = false)
            }
        }
    }

    // Date -> Long 변환
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    // Long -> Date 변환
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}