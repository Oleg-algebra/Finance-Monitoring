package com.example.financemonitoring.domain

import java.time.LocalDate

data class Filter(
    val categories: List<String> = listOf(),
    val fromDate: LocalDate? = null,
    val toDate: LocalDate? = null
)
