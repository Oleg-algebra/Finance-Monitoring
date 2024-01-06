package com.example.financemonitoring.domain.filter_comands

import android.util.Log
import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.presentation.main.MainActivity.Companion.TAG

class FilterCategory : Command {
    override fun execute(filter: Filter, records: List<FinanceRecord>): List<FinanceRecord> {
//        Log.d(TAG, "before execute category filter: ${records.size}")
        if(filter.categories != null) {
//            Log.d(TAG, "execute category filter: ${filter.categories.size} ")

            return records.filter { filter.categories.contains(it.category) }
        }
        return records
    }

}