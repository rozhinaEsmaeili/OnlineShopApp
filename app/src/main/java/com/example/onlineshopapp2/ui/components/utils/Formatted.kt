package com.example.onlineshopapp2.ui.components.utils

import androidx.compose.runtime.Composable


@Composable
fun formatted(price: Long?): String {
    val formatted = "%,d".format(price)
    return formatted
}
