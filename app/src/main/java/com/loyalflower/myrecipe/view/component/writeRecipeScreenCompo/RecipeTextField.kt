package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont

@Composable
fun RecipeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    done: Boolean,
    modifier: Modifier = Modifier,
    enable:Boolean
) {
    TextField(
        value = value, onValueChange = onValueChange, singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = MyRecipeTheme.dimens.paddingSmall),
        placeholder = {
            Text(
                text = label, style = TextStyle(
                    fontFamily = PretendardFont,
                    fontSize = MyRecipeTheme.dimens.textSizeNormal
                )
            )
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = PretendardFont,
            fontWeight = FontWeight.Medium,
            fontSize = MyRecipeTheme.dimens.textSizeNormal
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = BurnOrange,
            unfocusedPlaceholderColor = Color.LightGray,
            unfocusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledLabelColor = Color.Transparent,
            disabledPlaceholderColor = Color.LightGray,
            disabledSupportingTextColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (done) ImeAction.Done else ImeAction.Next
        ),
        enabled = enable
    )
}