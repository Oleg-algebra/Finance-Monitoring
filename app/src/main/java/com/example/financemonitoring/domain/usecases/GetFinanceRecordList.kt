package com.example.financemonitoring.domain.usecases

import androidx.lifecycle.LiveData
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class GetFinanceRecordList(
    private val repo: Repository
) {
    fun getRecordList(): LiveData<List<FinanceRecord>>{
        return repo.getListRecord()
    }
}