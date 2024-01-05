package com.example.financemonitoring.domain

import java.time.LocalDate

object Date {

    fun stringToDate(dateString: String): LocalDate {
        val dateComponents = dateString.split("-".toRegex())
        val day = dateComponents.get(0).toInt()
        val month = dateComponents.get(1).toInt()
        val year = dateComponents.get(2).toInt()

        return LocalDate.of(year,month,day)
    }

    fun stringToDateReverse(dateString: String): LocalDate{
        val dateComponents = dateString.split("-".toRegex()).reversed()
        return stringToDate(dateComponents.joinToString(separator = "-"))
    }
}