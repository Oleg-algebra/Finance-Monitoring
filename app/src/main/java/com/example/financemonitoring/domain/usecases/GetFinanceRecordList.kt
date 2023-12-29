package com.example.financemonitoring.domain.usecases

import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class GetFinanceRecordList(
    private val repo: Repository
) {
    fun getRecordList(){
        repo.getListRecord()
    }
}