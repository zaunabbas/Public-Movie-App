package com.zenjob.android.browsr.util

import java.text.SimpleDateFormat
import java.util.*

private const val DEFAULT_FORMAT = "dd/MM/yyyy"

fun Date.toCustomFormat(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(this)
}

fun Date.format(format: String = DEFAULT_FORMAT): String {
    val sfd = SimpleDateFormat(format, Locale.getDefault())
    return sfd.format(this)
}