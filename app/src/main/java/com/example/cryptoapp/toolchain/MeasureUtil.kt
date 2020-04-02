@file:Suppress("unused")

package com.example.cryptoapp.toolchain

import android.content.res.Resources
import android.view.View

fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun getViewHeightPx(v: View): Int {
    return v.layoutParams.height
}

//excluding system windows
fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

//excluding system windows
fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}
