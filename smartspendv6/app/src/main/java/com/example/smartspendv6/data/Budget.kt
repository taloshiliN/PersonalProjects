package com.example.smartspendv6.data

import java.time.LocalDateTime

data class Budget(
    val budgetName: String,
    val budgetAmount: Double,
    val entryDate: LocalDateTime = LocalDateTime.now(),
)
