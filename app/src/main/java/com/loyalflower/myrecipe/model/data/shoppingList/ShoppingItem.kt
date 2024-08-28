package com.loyalflower.myrecipe.model.data.shoppingList

//나중에 LazyColumn에서 살 것 아이템을 각각 인식해주기 위해 item에도 id 부여
data class ShoppingItem(
    val id:Long,
    val itemName: String, // 아이템 이름
    val isCompleted: Boolean = false // 해당 아이템의 완료 여부
)