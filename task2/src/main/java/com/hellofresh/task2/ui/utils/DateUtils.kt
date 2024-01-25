package com.hellofresh.task2.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
companion object{
    fun getCurrentDateInDdMmmFormat(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        return dateFormat.format(currentDate)
    }
}
}