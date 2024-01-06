package com.example.financemonitoring.domain.filter_comands

import android.util.Log
import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.presentation.main.MainActivity

class FilterFromDate : Command {
    override fun execute(filter: Filter, records: List<FinanceRecord>): List<FinanceRecord> {
        if(filter.fromDate != null){
//            Log.d(MainActivity.TAG, "execute from date filter: ")
            return records.filter { it.date >= filter.fromDate }
        }
        return records

    }
}