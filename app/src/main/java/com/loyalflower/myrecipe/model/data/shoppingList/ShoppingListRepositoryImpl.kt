package com.loyalflower.myrecipe.model.data.shoppingList

import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

//repository의 기능들 구현
class ShoppingListRepositoryImpl @Inject constructor(private val shoppingListDao: ShoppingListDao):
    ShoppingListRepository {

    override suspend fun insertShoppingList(shoppingList: ShoppingListEntity){
        shoppingListDao.insertShoppingList(shoppingList)
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingListEntity){
        shoppingListDao.updateShoppingList(shoppingList)
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingListEntity){
        shoppingListDao.deleteShoppingList(shoppingList)
    }

    override fun getShoppingListByDate(date: Date): Flow<ShoppingListEntity?>{
        return shoppingListDao.getShoppingListByDate(date)
    }

    override fun getAllShoppingLists(): Flow<List<ShoppingListEntity>>{
        return shoppingListDao.getAllShoppingLists()
    }

    override suspend fun deleteOldShoppingLists(thresholdDate: Date){
        shoppingListDao.deleteOldShoppingLists(thresholdDate)
    }

    override suspend fun deleteAllShoppingLists(){
        shoppingListDao.deleteAllShoppingLists()
    }

}