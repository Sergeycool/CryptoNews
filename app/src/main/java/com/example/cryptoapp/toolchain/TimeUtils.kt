package com.example.cryptoapp.toolchain

import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.App
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun convertTimestampToTime(timestamp: Long?): String {
    if (timestamp == null) return ""
    val stamp = Timestamp(timestamp * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

fun getDateFromTimestamp(timestamp: Long?): Date? {
    if (timestamp == null) return null
    val stamp = Timestamp(timestamp * 1000)
    return Date(stamp.time)
}

fun getTimeDifference(longTime: Long?): String {
    val result = ""
    val date: Date? = if (longTime != null) getDateFromTimestamp(longTime) else null
    if (date != null) {
        val diffInMillies = Date().time - date.time
        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillies)
        if (diffInDays > 0) {
            return String.format(App.context.getString(R.string.all_time_ago_days), diffInDays)
        }
        val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillies)
        if (diffInHours > 0) {
            return String.format(App.context.getString(R.string.all_time_ago_hours), diffInHours)
        }
        val diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies)
        if (diffInMinutes > 0) {
            return String.format(App.context.getString(R.string.all_time_ago_minutes), diffInMinutes)
        }

        return App.context.getString(R.string.all_time_ago_moment)
    }
    return result
}