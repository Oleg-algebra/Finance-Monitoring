package com.example.financemonitoring.domain.usecases

import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class AddFinanceRecord(
    private val repo: Repository
) {
    fun addRecord(record: FinanceRecord){
        repo.addRecord(record)
    }
}