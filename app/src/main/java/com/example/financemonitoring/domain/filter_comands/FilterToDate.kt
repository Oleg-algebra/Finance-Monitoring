package com.example.financemonitoring.domain.filter_comands

import android.util.Log
import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.presentation.main.MainActivity.Companion.TAG

class FilterToDate : Command {
    override fun execute(filter: Filter, records: List<FinanceRecord>): List<FinanceRecord> {
        if(filter.toDate != null){
//            Log.d(TAG, "execute to date filter: ")
            return records.filter { it.date <= filter.toDate }
        }
        return records
    }
}