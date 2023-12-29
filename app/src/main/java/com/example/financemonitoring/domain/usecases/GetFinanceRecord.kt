package com.example.financemonitoring.domain.usecases

import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class GetFinanceRecord(
    private val repo: Repository
) {
    fun getRecord(id: Long){
        repo.getRecord(id)
    }
}