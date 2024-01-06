package com.example.financemonitoring.domain

import java.time.LocalDate

data class Filter(
    val categories: List<String>? = null,
    val fromDate: LocalDate? = null,
    val toDate: LocalDate? = null,
    val activity: String = MAIN_ACTIVITY
)
