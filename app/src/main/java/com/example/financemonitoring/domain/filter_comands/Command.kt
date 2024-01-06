package com.example.financemonitoring.domain.filter_comands

import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.domain.FinanceRecord

interface Command {
    fun execute(filter: Filter, records: List<FinanceRecord>): List<FinanceRecord>
}