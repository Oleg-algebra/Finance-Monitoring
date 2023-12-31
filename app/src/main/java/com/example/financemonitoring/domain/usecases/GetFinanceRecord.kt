package com.example.financemonitoring.domain.usecases

import androidx.lifecycle.LiveData
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class GetFinanceRecord(
    private val repo: Repository
) {
    suspend fun getRecord(id: Long): LiveData<FinanceRecord>{
        return repo.getRecord(id)
    }
}