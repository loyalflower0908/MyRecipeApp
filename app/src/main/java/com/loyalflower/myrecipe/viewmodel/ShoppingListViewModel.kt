package com.loyalflower.myrecipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loyalflower.myrecipe.model.utils.DateUtils
import com.loyalflower.myrecipe.model.data.shoppingList.ShoppingItem
import com.loyalflower.myrecipe.model.data.shoppingList.ShoppingListEntity
import com.loyalflower.myrecipe.model.data.shoppingList.ShoppingListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(private val repository: ShoppingListRepositoryImpl) :
    ViewModel() {

    //오늘 살 것 리스트
    private val _shoppingList = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val shoppingList: StateFlow<List<ShoppingItem>> = _shoppingList.asStateFlow()

    //현재 날짜(선택된 날짜)
    private val _selectedDate = MutableStateFlow(DateUtils.resetTime(Date()))
    val selectedDate: StateFlow<Date> = _selectedDate.asStateFlow()

    //다이어로그 표시 제어
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    init {
        // 앱 시작 시 1달 이전의 데이터 삭제
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, -1) // 1달 전 날짜 계산
            val thresholdDate = calendar.time
            repository.deleteOldShoppingLists(thresholdDate)
        }
    }

    //날짜 설정, 설정했을때 살 것 리스트 다시 불러옴(한 번만)
    fun setDate(date: Date) {
        _selectedDate.value = DateUtils.resetTime(date)

        viewModelScope.launch {
            selectedDate.collect { date ->
                _shoppingList.value = repository.getShoppingListByDate(date)
                    .map { it?.items ?: emptyList() }
                    .first()
            }
        }
    }

    //새로운 살 것 리스트에 추가. update 해주고 UI에도 업데이트 해주기
    fun addItem(itemName: String) {
        viewModelScope.launch {
            val currentList = repository.getShoppingListByDate(_selectedDate.value).firstOrNull()
            val updatedList = (currentList?.items?.toMutableList() ?: mutableListOf()).apply {
                add(ShoppingItem(id = generateUniqueId(), itemName = itemName))
            }
            val updatedEntity = ShoppingListEntity(
                id = currentList?.id ?: 0,
                date = _selectedDate.value,
                items = updatedList
            )
            repository.insertShoppingList(updatedEntity)

            _shoppingList.value = updatedList
        }
    }

    //isCompleted 상태를 갱신해주는 함수
    fun updateItemStatus(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            val currentEntity = repository.getShoppingListByDate(_selectedDate.value).firstOrNull()
            currentEntity?.let {
                val updatedList = it.items.map { existingItem ->
                    if (existingItem.id == id) {
                        existingItem.copy(isCompleted = isCompleted)
                    } else {
                        existingItem
                    }
                }

                val updatedEntity = it.copy(items = updatedList)
                repository.insertShoppingList(updatedEntity)

                _shoppingList.value = updatedList
            }
        }
    }

    //살 것 (아이템)을 리스트에서 삭제하고 반영
    fun deleteShoppingListItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            val currentEntity = repository.getShoppingListByDate(_selectedDate.value).firstOrNull()
            currentEntity?.let {
                val updatedList = it.items.toMutableList().apply {
                    removeAll { item -> item.id == shoppingItem.id }
                }

                val updatedEntity = it.copy(items = updatedList)
                repository.insertShoppingList(updatedEntity)

                _shoppingList.value = updatedList
            }
        }
    }

    //유니크한 id를 생성하기 위한 함수
    private fun generateUniqueId(): Long {
        return UUID.randomUUID().mostSignificantBits
    }

    //다이어로그 보여주는거 제어 함수
    fun setShowDialog(show: Boolean) {
        _showDialog.value = show
    }
}
