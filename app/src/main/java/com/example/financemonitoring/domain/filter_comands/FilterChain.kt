package com.example.financemonitoring.domain.filter_comands

import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.domain.FinanceRecord

object FilterChain : Command {
    private val chain = listOf(
        FilterCategory(),
        FilterFromDate(),
        FilterToDate()
    )
    override fun execute(filter: Filter, records: List<FinanceRecord>): List<FinanceRecord> {
        var list = records.toList()
        for(f in chain){
            list = f.execute(filter,list)
        }
        return list
    }
}