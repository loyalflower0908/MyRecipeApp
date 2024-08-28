package com.loyalflower.myrecipe.model.data.shoppingList

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingListEntity)

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)

    //날짜로 살 것 리스트 받기
    @Query("SELECT * FROM shopping_list WHERE date = :date")
    fun getShoppingListByDate(date: Date): Flow<ShoppingListEntity?>

    @Query("SELECT * FROM shopping_list")
    fun getAllShoppingLists(): Flow<List<ShoppingListEntity>>

    //오래된 살 것 목록 삭제
    @Query("DELETE FROM shopping_list WHERE date < :thresholdDate")
    suspend fun deleteOldShoppingLists(thresholdDate: Date)

    //모든 살 것 리스트 삭제
    @Query("DELETE FROM shopping_list")
    suspend fun deleteAllShoppingLists()
}