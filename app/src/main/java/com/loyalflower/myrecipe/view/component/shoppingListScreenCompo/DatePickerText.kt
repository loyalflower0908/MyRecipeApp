package com.loyalflower.myrecipe.view.component.shoppingListScreenCompo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


@SuppressLint("SimpleDateFormat")
@Composable
fun BoxScope.DatePickerText(
    currentDate: Date,
    onDateSelected: (Date) -> Unit
) {
    val context = LocalContext.current
    Text(
        text = SimpleDateFormat("yyyy/MM/dd").format(currentDate),
        style = TextStyle(
            fontFamily = PretendardFont,
            fontWeight = FontWeight.Bold,
            fontSize = MyRecipeTheme.dimens.textSizeNormal,
            color = Color.White
        ),
        modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                val calendar = Calendar
                    .getInstance()
                    .apply {
                        time = currentDate
                    }
                DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val newDate = Calendar
                                .getInstance()
                                .apply {
                                    set(year, month, dayOfMonth)
                                }.time
                            onDateSelected(newDate)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    .show()
            }
    )
}