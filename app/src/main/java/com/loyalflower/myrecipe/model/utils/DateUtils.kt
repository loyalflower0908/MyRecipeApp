package com.loyalflower.myrecipe.model.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

object DateUtils {
    fun resetTime(date: Date): Date {
        //시간 제외하고 날짜로 통일시켜서 써야함
        val calendar = Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return calendar.time
    }

    //텍스트를 시간 제외하고 날짜만 가져오기 위한 formatting
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(input: String): String {
        return try {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val date = LocalDate.parse(input, formatter)
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // 원하는 날짜 형식으로 변경
            date.format(outputFormatter)
        } catch (e: Exception) {
            input // 포맷팅 중 오류 발생 시 원본 문자열 반환
        }
    }
}