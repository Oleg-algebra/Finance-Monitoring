package com.example.financemonitoring.presentation

import java.time.LocalDate

data class Filter(
    val categories: List<String>,
    val minChange: Long,
    val maxChange: Long,
    val fromDate: LocalDate,
    val toDate: LocalDate
)
