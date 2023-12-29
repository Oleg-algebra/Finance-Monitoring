package com.example.financemonitoring.domain.usecases

import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class EditFinanceRecord(
    private val repo: Repository
) {
    fun editRecord(record: FinanceRecord){
        repo.editRecord(record)
    }
}