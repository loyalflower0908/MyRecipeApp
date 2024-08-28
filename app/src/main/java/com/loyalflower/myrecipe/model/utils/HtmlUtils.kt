package com.loyalflower.myrecipe.model.utils

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html

object HtmlUtils {
    //문자에 들어간 HTML 태그등을 풀어주는 함수
    @SuppressLint("ObsoleteSdkInt")
    fun decodeHtmlEntities(input: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(input).toString()
        }
    }
}