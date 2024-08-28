package com.loyalflower.myrecipe.view.component.shoppingListScreenCompo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.viewmodel.ShoppingListViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun DateSelectSection(
    selectedDate: Date,
    viewModel: ShoppingListViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BurnOrange)
    ) {
        Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerMedium))
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "어제",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = MyRecipeTheme.dimens.paddingLarge)
                    .align(
                        Alignment.CenterStart
                    )
                    .size(MyRecipeTheme.dimens.iconSizeNormal)
                    .clickable {
                        val previousDate = Calendar
                            .getInstance()
                            .apply {
                                time = selectedDate
                                add(Calendar.DAY_OF_YEAR, -1)
                            }.time
                        viewModel.setDate(previousDate)
                    }
            )
            //클릭시 달력(데이트 Picker)를 띄워서 선택
            DatePickerText(currentDate = selectedDate) { selectedDate ->
                viewModel.setDate(selectedDate)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "내일",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = MyRecipeTheme.dimens.paddingLarge)
                    .align(
                        Alignment.CenterEnd
                    )
                    .size(MyRecipeTheme.dimens.iconSizeNormal)
                    .clickable {
                        val nextDate = Calendar
                            .getInstance()
                            .apply {
                                time = selectedDate
                                add(Calendar.DAY_OF_YEAR, 1)
                            }.time
                        viewModel.setDate(nextDate)
                    }
            )
        }
        Spacer(modifier = Modifier.height(MyRecipeTheme.dimens.spacerMedium))
        HorizontalDivider(color = Color.LightGray.copy(0.3f))
    }
}
