package com.example.financemonitoring.domain

import androidx.lifecycle.LiveData

interface Repository {

    suspend fun addRecord(record: FinanceRecord)
    suspend fun editRecord(record: FinanceRecord)
    fun getRecord(id: Long): LiveData<FinanceRecord>
    fun getListRecord(): LiveData<List<FinanceRecord>>
    suspend fun removeRecord(record: FinanceRecord)

}