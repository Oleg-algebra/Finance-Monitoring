package com.example.financemonitoring.domain

import java.time.LocalDate

data class FinanceRecord(
    val id: Long,
    val name: String,
    val change: Long,
    val category: String,
    val date: LocalDate
)
