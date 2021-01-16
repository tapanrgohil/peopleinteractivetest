package com.shaadi.test.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.*


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Date.toReadableDate(): String {
    var sdf: SimpleDateFormat = SimpleDateFormat("dd MMM yyyy")
    return sdf.format(this)
}
