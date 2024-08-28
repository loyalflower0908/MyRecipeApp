package com.loyalflower.myrecipe.model

enum class NavigationBarItemModel {
    MYRECIPE,
    TODAYCHECKLIST,
    SEARCHRECIPE,
    REPOSITORY
}

data class NaviBarItemData(val item: NavigationBarItemModel, val iconRes: Int, val label: String)