package com.loyalflower.myrecipe.view.component.writeRecipeScreenCompo

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.loyalflower.myrecipe.R
import com.loyalflower.myrecipe.view.theme.MyRecipeTheme

@Composable
fun RecipeImageSection(
    openDocumentLauncher: ManagedActivityResultLauncher<Array<String>, Uri?>,
    imageUri: Uri?,
    bitmap: Bitmap?,
    writingMode:Boolean
) {
    SectionHeader(title = "음식 사진")
    //쓰기 모드일 땐 터치시 이미지 불러오기, 사진 없을 땐 사진 업로드 이미지
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.LightGray)
            .clickable(enabled = writingMode) { openDocumentLauncher.launch(arrayOf("image/*")) }
    ) {
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(model = bitmap),
                contentDescription = "완성된 음식 사진",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } ?: run {
            Icon(
                painter = painterResource(id = R.drawable.image_upload),
                contentDescription = "음식 사진 넣기",
                tint = Color.Gray,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(MyRecipeTheme.dimens.iconSizeNormal)
            )
        }
    }
}