package com.example.financemonitoring.domain.usecases

import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class RemoveFinanceRecord(
    private val repo: Repository
) {
    fun removeRecord(record: FinanceRecord){
        repo.removeRecord(record)
    }
}