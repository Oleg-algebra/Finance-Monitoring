package com.example.financemonitoring.domain

import java.time.LocalDate

data class FinanceRecord(
    val id: Long = UNDEFINED_ID,
    val name: String = UNDEFINED_NAME,
    val change: Long = UNDEFINED_CHANGE,
    val category: String = UNDEFINED_CATEGORY,
    val date: LocalDate = LocalDate.now()
)
