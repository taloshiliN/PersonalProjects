package com.example.smartspendv6.data

data class Expenses(
    val name: String,
    val date: Long,
    val amount: Double,
    val notes: String?,
    val receipt: String?,
    val categoryId: String
)
