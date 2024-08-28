package com.loyalflower.myrecipe.model.data.shoppingList


import kotlinx.coroutines.flow.Flow
import java.util.Date

//repository 청사진
interface ShoppingListRepository {
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

    suspend fun updateShoppingList(shoppingList: ShoppingListEntity)

    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)

    fun getShoppingListByDate(date: Date): Flow<ShoppingListEntity?>

    fun getAllShoppingLists(): Flow<List<ShoppingListEntity>>

    suspend fun deleteOldShoppingLists(thresholdDate: Date)

    suspend fun deleteAllShoppingLists()
}