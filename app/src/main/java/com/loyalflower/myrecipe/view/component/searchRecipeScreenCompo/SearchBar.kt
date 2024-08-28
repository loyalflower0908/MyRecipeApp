package com.loyalflower.myrecipe.view.component.searchRecipeScreenCompo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.loyalflower.myrecipe.R
import com.loyalflower.myrecipe.view.theme.BurnOrange
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme
import com.loyalflower.myrecipe.view.theme.PretendardFont


@Composable
fun SearchBar(modifier: Modifier = Modifier, onCancle: () -> Unit, onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = {
            Text(
                "레시피 검색", style = TextStyle(
                    fontFamily = PretendardFont,
                    fontSize = MyRecipeTheme.dimens.textSizeNormal
                )
            )
        },
        leadingIcon = {
            if (text.isNotBlank()) IconButton(onClick = {
                text = ""
                onCancle()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "cancel",
                    modifier = Modifier.size(MyRecipeTheme.dimens.iconSizeSmall)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { onSearch(text) }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(MyRecipeTheme.dimens.iconSizeSmall)
                )
            }
        },
        modifier = modifier.border(
            width = 3.dp,
            color = BurnOrange,
            shape = RoundedCornerShape(20)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearch(text) }),
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = PretendardFont, color = Color.Black, fontWeight = FontWeight.SemiBold,
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
    )
}