package com.example.financemonitoring.presentation

import java.io.Serializable
import java.time.LocalDate

data class Filter(
    val names: List<String>,
    val categories: List<String>,
    val minChange: Long,
    val maxChange: Long,
    val fromDate: LocalDate,
    val toDate: LocalDate
)
