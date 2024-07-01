package com.example.smartspendv6.data

import androidx.compose.ui.graphics.Brush

data class Card(
    val cardType: String,
    val cardNumber: String,
    val cardYear: String,
    //val cardName: String,
    val balance: Double,
    val cvv: String,

    val color: Brush
)
